package beachcombing.backend.domain.refresh_token.domain;

import beachcombing.backend.domain.common.domain.BaseEntity;
import beachcombing.backend.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class RefreshToken extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private Long id;
    @Column(length=600)
    private String refreshToken;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static RefreshToken createRefreshToken(String refreshToken, Member member) {
        return RefreshToken.builder()
                .refreshToken(refreshToken)
                .member(member)
                .build();
    }

    public void updateToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}