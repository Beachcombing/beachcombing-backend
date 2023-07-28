package beachcombing.backend.domain.auth.service;

import beachcombing.backend.domain.auth.dto.AuthJoinRequest;
import beachcombing.backend.domain.auth.dto.AuthLoginRequest;
import beachcombing.backend.domain.auth.dto.AuthLoginResponse;
import beachcombing.backend.domain.token_pair.service.TokenPairService;
import beachcombing.backend.domain.user.domain.User;
import beachcombing.backend.domain.user.mapper.UserMapper;
import beachcombing.backend.domain.user.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenPairService tokenPairService;

    // 일반 회원가입 (테스트용)
    public void join(AuthJoinRequest authJoinRequest) {

        User user = userMapper.toEntity(authJoinRequest);
        userRepository.save(user);
    }

    // 일반 로그인
    public AuthLoginResponse login(AuthLoginRequest request) {

        // 아이디와 비밀번호 유효성 체크
        User user = userRepository.findByAuthInfoLoginId(request.getLoginId());

        if(user == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_ID);
        }
        if(!passwordEncoder.matches(request.getPassword(), user.getAuthInfo().getPassword())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_PASSWORD);
        }

        // 토큰 생성
        String accessToken = jwtTokenProvider.generateAccessToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);
        tokenPairService.saveTokenPair(accessToken, refreshToken, user);

        AuthLoginResponse response = AuthLoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return response;
    }
}

