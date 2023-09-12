package beachcombing.backend.domain.refresh_token.domain;

import beachcombing.backend.domain.common.domain.BaseEntity;
import beachcombing.backend.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RefreshToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private Long id;
    
    @Column(length=600)
    private String refreshToken;

    private String keyLoginId;
    
    @Builder
    public RefreshToken(String refreshToken, String keyLoginId) {
        this.refreshToken = refreshToken;
        this.keyLoginId = keyLoginId;
    }

    public static RefreshToken createRefreshToken(String refreshToken, String keyLoginId) {
        return RefreshToken.builder()
                .refreshToken(refreshToken)
                .keyLoginId(keyLoginId)
                .build();
    }

    public void updateToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}