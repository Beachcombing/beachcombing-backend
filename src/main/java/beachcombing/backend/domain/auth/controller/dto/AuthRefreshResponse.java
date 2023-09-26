package beachcombing.backend.domain.auth.controller.dto;

import beachcombing.backend.domain.common.domain.Role;
import beachcombing.backend.domain.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthRefreshResponse {
    private String accessToken;
    private Role role;

    public static AuthRefreshResponse of(String accessToken, Member member){
        return AuthRefreshResponse.builder()
                .accessToken(accessToken)
                .role(member.getProfile().getRole())
                .build();
    }


}
