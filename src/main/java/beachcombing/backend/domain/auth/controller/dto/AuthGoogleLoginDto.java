package beachcombing.backend.domain.auth.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthGoogleLoginDto {
    public String sub;
    public String name;
    public String email;
    public String picture;
}
