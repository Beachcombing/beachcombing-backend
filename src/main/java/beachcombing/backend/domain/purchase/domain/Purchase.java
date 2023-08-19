package beachcombing.backend.domain.purchase.domain;

import beachcombing.backend.domain.giftcard.domain.Giftcard;
import beachcombing.backend.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase {
    @Id
    @Column(name = "purchase_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 구매 id

    @ManyToOne
    @JoinColumn(name = "giftcard_id")
    private Giftcard giftcard; // 구매한 상품권

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // 구매한 회원

    @Builder
    public Purchase(Giftcard giftcard, Member member) {
        this.giftcard = giftcard;
        this.member = member;
    }

    public static Purchase createPurchase(Giftcard giftcard, Member member) {
        return Purchase.builder()
                .giftcard(giftcard)
                .member(member)
                .build();
    }

}
