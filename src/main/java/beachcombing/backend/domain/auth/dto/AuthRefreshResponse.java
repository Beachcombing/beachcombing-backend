package beachcombing.backend.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthRefreshResponse {
    private String accessToken;
    private String role;
}
