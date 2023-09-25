package beachcombing.backend.domain.auth.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthGoogleLoginRequest {
    public String idToken;


}
