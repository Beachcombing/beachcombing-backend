package beachcombing.backend.domain.member.domain;

import beachcombing.backend.domain.common.domain.Provider;
import jakarta.persistence.Embeddable;
import lombok.*;


@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthInfo {
    private String loginId; // 로컬 로그인 확장 대비. username 역할
    private String password; // 일반 로그인 위함
    private Provider provider; // 소셜로그인 구분 위함.

    @Builder
    public AuthInfo(String loginId, String password, Provider provider) {
        this.loginId = loginId;
        this.password = password;
        this.provider = provider;
    }

    public static AuthInfo createAuthInfo(String loginId, String password, Provider provider){
        return AuthInfo.builder()
                .loginId(loginId)
                .password(password)
                .provider(provider)
                .build();
    }
}