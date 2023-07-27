package beachcombing.backend.domain.user.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthInfo {
    private String loginId;    // 아이디
    private String password;    // 비밀번호

    @Builder
    public AuthInfo(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}