package beachcombing.backend.domain.auth.controller.dto;

import beachcombing.backend.domain.member.domain.AuthInfo;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.domain.Profile;
import lombok.AllArgsConstructor;
import beachcombing.backend.domain.common.domain.Provider;
import beachcombing.backend.domain.common.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@NoArgsConstructor
@Getter
public class AuthJoinRequest {

    private String loginId;
    private String password;
    private Provider provider;
    private String email;
    private String nickName;
    private String image;
    private Role role;
}