package beachcombing.backend.domain.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

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
}