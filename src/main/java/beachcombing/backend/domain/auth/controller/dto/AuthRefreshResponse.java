package beachcombing.backend.domain.auth.controller.dto;

import beachcombing.backend.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthRefreshResponse {
    private String accessToken;
    private String role;

    public static AuthRefreshResponse of(String accessToken, Member member){
        return AuthRefreshResponse.builder()
                .accessToken(accessToken)
                .role(member.getProfile().getRole())
                .build();
    }



}
