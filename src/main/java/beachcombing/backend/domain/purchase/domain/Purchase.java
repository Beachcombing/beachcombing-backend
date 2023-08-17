package beachcombing.backend.domain.purchase;

import beachcombing.backend.domain.giftcard.domain.Giftcard;
import beachcombing.backend.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase {
    @Id
    @Column(name = "purchase_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "giftcard_id")
    private Giftcard giftcard;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Purchase(Long id, Giftcard giftcard, Member member) {
        this.id = id;
        this.giftcard = giftcard;
        this.member = member;
    }

    public Purchase createPurchase(Long id, Giftcard giftcard, Member member) {
        return Purchase.builder()
                .giftcard(giftcard)
                .id(id)
                .member(member)
                .build();
    }

}
