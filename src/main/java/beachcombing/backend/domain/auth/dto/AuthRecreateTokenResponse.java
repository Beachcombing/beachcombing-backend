package beachcombing.backend.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class AuthRecreateTokenResponse {
    private String accessToken;
    private String role;
}
