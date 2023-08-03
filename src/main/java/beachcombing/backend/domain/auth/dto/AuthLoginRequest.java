package beachcombing.backend.domain.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AuthLoginRequest {

    private String loginId;
    private String password;
}