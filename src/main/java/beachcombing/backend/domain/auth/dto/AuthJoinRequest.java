package beachcombing.backend.domain.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AuthJoinRequest {

    private String loginId;
    private String password;
    private String name;
    private String email;
    private String phone;
}