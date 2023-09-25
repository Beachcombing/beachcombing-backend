package beachcombing.backend.domain.auth.controller.dto;

import beachcombing.backend.domain.common.domain.Role;
import beachcombing.backend.domain.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthLoginResponse {
    private String accessToken;
  
    private String refreshToken;
  
    private Role role;

    public static AuthLoginResponse of(String accessToken, String refreshToken, Member member){
        return AuthLoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .role(member.getProfile().getRole())
                .build();
    }
}