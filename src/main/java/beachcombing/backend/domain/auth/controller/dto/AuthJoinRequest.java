package beachcombing.backend.domain.auth.controller.dto;

import beachcombing.backend.domain.member.domain.AuthInfo;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.domain.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@NoArgsConstructor
@Getter
public class AuthJoinRequest {

    private String loginId;
    private String password;
    private String provider;
    private String email;
    private String nickName;
    private String image;
    private String role;

    /*
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthInfo toAuthInfo() {

        return AuthInfo.builder()
                .loginId(loginId)
                .password(bCryptPasswordEncoder.encode(password))
                .provider(provider)
                .build();
    }

    private Profile toProfile() {

        return Profile.builder()
                .email(email)
                .nickname(nickName)
                .image(image)
                .role(role)
                .build();
    }
    public Member toEntity() {

        AuthInfo authInfo = toAuthInfo();
        Profile profile = toProfile();
        return Member.createMember(profile, authInfo);
    }
*/
}