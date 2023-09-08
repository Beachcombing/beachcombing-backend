package beachcombing.backend.domain.purchase.mapper;

import beachcombing.backend.domain.giftcard.domain.Giftcard;
import beachcombing.backend.domain.giftcard.dto.PurchaseUpdateResponse;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.purchase.domain.Purchase;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMapper {

    public PurchaseUpdateResponse toPurchaseIdResponse(Purchase purchase) {
        return PurchaseUpdateResponse.builder()
                .id(purchase.getId())
                .build();
    }

    public Purchase toPurchase(Member member, Giftcard giftcard) {
        return Purchase.builder()
                .member(member)
                .giftcard(giftcard)
                .build();
    }

}
