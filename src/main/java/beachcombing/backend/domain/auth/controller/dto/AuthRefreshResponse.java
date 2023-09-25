package beachcombing.backend.domain.auth.controller.dto;

import beachcombing.backend.domain.common.domain.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthRefreshResponse {
    private String accessToken;
    private Role role;
}
