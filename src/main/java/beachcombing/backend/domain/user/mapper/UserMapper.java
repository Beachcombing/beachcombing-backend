package beachcombing.backend.domain.user.mapper;

import beachcombing.backend.domain.auth.dto.AuthJoinRequest;
import beachcombing.backend.domain.user.domain.AuthInfo;
import beachcombing.backend.domain.user.domain.Profile;
import beachcombing.backend.domain.user.domain.User;
import lombok.RequiredArgsConstructor;;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthInfo toAuthInfo(String loginId, String password) {

        return AuthInfo.builder()
                .loginId(loginId)
                .password(bCryptPasswordEncoder.encode(password))
                .build();
    }

    public Profile toProfile(String name, String email, String phone) {

        return Profile.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .build();
    }

    public User toEntity(AuthJoinRequest request) {

        AuthInfo authInfo = toAuthInfo(
                request.getLoginId(),
                request.getPassword());

        Profile profile = toProfile(
                request.getName(),
                request.getEmail(),
                request.getPhone());

        return User.createUser(profile, authInfo);
    }
}
