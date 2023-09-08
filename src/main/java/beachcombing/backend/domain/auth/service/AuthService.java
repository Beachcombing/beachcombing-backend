package beachcombing.backend.domain.auth.service;

import beachcombing.backend.domain.auth.dto.AuthJoinRequest;
import beachcombing.backend.domain.auth.dto.AuthLoginRequest;
import beachcombing.backend.domain.auth.dto.AuthLoginResponse;
import beachcombing.backend.domain.auth.dto.AuthRefreshResponse;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.mapper.MemberMapper;
import beachcombing.backend.domain.member.repository.MemberRepository;
import beachcombing.backend.domain.refresh_token.domain.RefreshToken;
import beachcombing.backend.domain.refresh_token.repository.RefreshTokenRepository;
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

    // 일반 회원가입 (테스트용)
    public void join(AuthJoinRequest authJoinRequest) {

        Member member = memberMapper.toEntity(authJoinRequest);
        memberRepository.save(member);
    }

    // 일반 로그인
    public AuthLoginResponse login(AuthLoginRequest request) {

        // 아이디와 비밀번호 유효성 체크
        Member member = memberRepository.findByAuthInfoLoginId(request.getLoginId());

        if(member == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_ID);
        }
        if(!passwordEncoder.matches(request.getPassword(), member.getAuthInfo().getPassword())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_PASSWORD);
        }

        // 토큰 생성
        String accessToken = jwtTokenProvider.generateAccessToken(member);
        String refreshToken = jwtTokenProvider.generateRefreshToken(member);
        refreshTokenService.saveRefreshToken(refreshToken, member.getAuthInfo().getLoginId());

        AuthLoginResponse response = AuthLoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .role(member.getProfile().getRole())
                .build();

        return response;
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

        Member findMember = memberRepository.findByAuthInfoLoginId(loginId);
        String createdAccessToken = jwtTokenProvider.generateAccessToken(findMember);

        if (createdAccessToken == null) {
            throw new CustomException(ErrorCode.TOKEN_EXPIRED);
        }

        AuthRefreshResponse response = AuthRefreshResponse.builder()
                .accessToken(createdAccessToken)
                .role(findMember.getProfile().getRole())
                .build();

        return response;
    }

    public void logout(String request) {

        String accessToken = request.replace("Bearer ", "");
        Long expiration = jwtTokenProvider.validateAccessToken(accessToken);

//        redisTemplate.opsForValue()
//                .set(accessToken, "blackList", expiration, TimeUnit.MILLISECONDS);
    }
}

