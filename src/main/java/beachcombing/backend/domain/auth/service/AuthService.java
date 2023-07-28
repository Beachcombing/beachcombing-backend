package beachcombing.backend.domain.auth.service;

import beachcombing.backend.domain.auth.dto.AuthJoinRequest;
import beachcombing.backend.domain.auth.dto.AuthLoginRequest;
import beachcombing.backend.domain.auth.dto.AuthLoginResponse;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.token_pair.service.TokenPairService;
import beachcombing.backend.domain.member.mapper.MemberMapper;
import beachcombing.backend.domain.member.repository.MemberRepository;
import beachcombing.backend.global.config.exception.CustomException;
import beachcombing.backend.global.config.exception.ErrorCode;
import beachcombing.backend.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenPairService tokenPairService;

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
        tokenPairService.saveTokenPair(accessToken, refreshToken, member);

        AuthLoginResponse response = AuthLoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return response;
    }
}

