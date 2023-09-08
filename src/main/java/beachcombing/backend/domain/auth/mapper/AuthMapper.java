package beachcombing.backend.domain.auth.mapper;

import beachcombing.backend.domain.auth.dto.AuthLoginResponse;
import beachcombing.backend.domain.auth.dto.AuthRefreshResponse;
import beachcombing.backend.domain.member.domain.Member;

public class AuthMapper {

    public AuthLoginResponse toAuthLoginResponse(String accessToken,String refreshToken, Member member){
        return AuthLoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .role(member.getProfile().getRole())
                .build();
    }

    public AuthRefreshResponse toAuthRefreshResponse(String accessToken, Member member){
        return AuthRefreshResponse.builder()
                .accessToken(accessToken)
                .role(member.getProfile().getRole())
                .build();
    }


}
