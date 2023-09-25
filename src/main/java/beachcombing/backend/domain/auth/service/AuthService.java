package beachcombing.backend.domain.auth.service;

import beachcombing.backend.domain.auth.service.helper.GoogleLoginHelper;
import beachcombing.backend.domain.auth.controller.dto.AuthJoinRequest;
import beachcombing.backend.domain.auth.controller.dto.AuthLoginRequest;
import beachcombing.backend.domain.auth.controller.dto.AuthLoginResponse;
import beachcombing.backend.domain.auth.controller.dto.AuthRefreshResponse;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.mapper.MemberMapper;
import beachcombing.backend.domain.member.domain.repository.MemberRepository;
import beachcombing.backend.domain.refresh_token.domain.RefreshToken;
import beachcombing.backend.domain.refresh_token.domain.repository.RefreshTokenRepository;
import beachcombing.backend.domain.refresh_token.service.RefreshTokenService;
import beachcombing.backend.global.config.exception.CustomException;
import beachcombing.backend.global.config.exception.ErrorCode;
import beachcombing.backend.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final GoogleLoginHelper googleLoginHelper;
    private final AuthMapper authMapper;

    // 일반 회원가입 (테스트용)
    public void join(AuthJoinRequest authJoinRequest) {

        Member member = memberMapper.toEntity(authJoinRequest);
        memberRepository.save(member);
    }

    // 일반 로그인
    public AuthLoginResponse login(AuthLoginRequest request) {

        // 아이디와 비밀번호 유효성 체크
        Member member = memberRepository.findByAuthInfoLoginId(request.getLoginId()).orElse(null);

        if(member == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_ID);
        }

        if(!passwordEncoder.matches(request.getPassword(), member.getAuthInfo().getPassword())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_PASSWORD);
        }

       return createAndSaveToken(member); // 토큰 생성 및 저장
    }

    // 구글 로그인
    public AuthLoginResponse googleLogin(AuthGoogleLoginRequest request) {
        Member member = googleLoginHelper.getUserData(request.idToken); // IdToken으로 구글에서 유저 정보 받아오기

        Member findMember = memberRepository.findByAuthInfoLoginId(member.getAuthInfo().getLoginId())
                .orElse(null);
        if(findMember == null){ // 최초 로그인이라면 회원가입 시키기
            memberRepository.save(member);
        }

       return createAndSaveToken(member); // 토큰 생성 및 저장
    }

    // accessToken 재발급
    public AuthRefreshResponse refresh(String request) {
        String refreshToken = request.replace("Bearer", "");

        //refreshToken 유효성 확인
        jwtTokenProvider.validateRefreshToken(refreshToken);

        String loginId = jwtTokenProvider.getUsernameFromRefreshToken(refreshToken);
        RefreshToken findRefreshToken = refreshTokenRepository.findByKeyLoginId(loginId)
                .orElseThrow(() -> new CustomException(ErrorCode.TOKEN_INVALID));

        if (!refreshToken.equals(findRefreshToken.getRefreshToken())) {
            throw new CustomException(ErrorCode.TOKEN_INVALID);
        }

        Member member = memberRepository.findByAuthInfoLoginId(loginId)
                .orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_MEMBER));
        String createdAccessToken = jwtTokenProvider.generateAccessToken(member);

        if (createdAccessToken == null) {
            throw new CustomException(ErrorCode.TOKEN_EXPIRED);
        }

        return AuthRefreshResponse.of(createdAccessToken, member);
    }

    public void logout(String request) {

        String accessToken = request.replace("Bearer ", "");
        Long expiration = jwtTokenProvider.validateAccessToken(accessToken);

//        redisTemplate.opsForValue()
//                .set(accessToken, "blackList", expiration, TimeUnit.MILLISECONDS);
    }

    private AuthLoginResponse createAndSaveToken(Member member){
        String accessToken = jwtTokenProvider.generateAccessToken(member);
        String refreshToken = jwtTokenProvider.generateRefreshToken(member);

        refreshTokenService.saveRefreshToken(refreshToken, member.getAuthInfo().getLoginId());

        return AuthLoginResponse.of(accessToken, refreshToken, member);
    }

}

