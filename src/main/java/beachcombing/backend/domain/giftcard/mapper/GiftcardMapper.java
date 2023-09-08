package beachcombing.backend.domain.giftcard.mapper;

import beachcombing.backend.domain.giftcard.domain.Giftcard;
import beachcombing.backend.domain.giftcard.dto.GiftcardFindAllResponse;
import beachcombing.backend.domain.giftcard.dto.PurchaseFindAllResponse;
import org.springframework.stereotype.Component;

@Component
public class GiftcardMapper {

    public GiftcardFindAllResponse toGiftcardFindAllResponse(Giftcard giftcard) {
        return GiftcardFindAllResponse.builder()
                .id(giftcard.getId())
                .name(giftcard.getStore().getName())
                .location(giftcard.getStore().getLocation())
                .image(giftcard.getStore().getImage())
                .cost(giftcard.getCost())
                .build();
    }

    public PurchaseFindAllResponse toPurchaseFindAllResponse(Giftcard giftcard) {
        return PurchaseFindAllResponse.builder()
                .id(giftcard.getId())
                .name(giftcard.getStore().getName())
                .location(giftcard.getStore().getLocation())
                .image(giftcard.getStore().getImage())
                .cost(giftcard.getCost())
                .build();
    }
}
