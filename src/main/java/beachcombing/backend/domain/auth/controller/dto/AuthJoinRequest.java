package beachcombing.backend.domain.auth.controller.dto;

import beachcombing.backend.domain.common.domain.Provider;
import beachcombing.backend.domain.common.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

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