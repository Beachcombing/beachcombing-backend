package beachcombing.backend.domain.purchase.mapper;

import beachcombing.backend.domain.giftcard.domain.Giftcard;
import beachcombing.backend.domain.giftcard.dto.PurchaseSaveResponse;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.purchase.domain.Purchase;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMapper {

    public PurchaseSaveResponse toPurchaseIdResponse(Purchase purchase) {
        return PurchaseSaveResponse.builder()
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
