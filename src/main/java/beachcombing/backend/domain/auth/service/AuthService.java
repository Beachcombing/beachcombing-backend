package beachcombing.backend.domain.auth.service;

import beachcombing.backend.domain.auth.dto.AuthJoinRequest;
import beachcombing.backend.domain.user.domain.User;
import beachcombing.backend.domain.user.mapper.UserMapper;
import beachcombing.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // 일반 회원가입 (테스트용)
    public void join(AuthJoinRequest authJoinRequest) {

        User user = userMapper.toEntity(authJoinRequest);
        userRepository.save(user);
    }
}
