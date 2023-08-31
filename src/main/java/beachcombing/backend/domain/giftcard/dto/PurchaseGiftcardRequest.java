package beachcombing.backend.domain.giftcard.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PurchaseGiftcardRequest {
    private Long memberId;
    private Long giftcardId;
}
