package beachcombing.backend.domain.auth.controller.dto;

import beachcombing.backend.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginResponse {
    private String accessToken;
  
    private String refreshToken;
  
    private String role;

    public static AuthLoginResponse of(String accessToken, String refreshToken, Member member){
        return AuthLoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .role(member.getProfile().getRole())
                .build();
    }

}