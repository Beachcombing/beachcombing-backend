package beachcombing.backend.domain.auth.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthLoginResponse {
    private String accessToken;
  
    private String refreshToken;
  
    private String role;
}