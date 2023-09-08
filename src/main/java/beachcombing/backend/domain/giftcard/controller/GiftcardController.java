package beachcombing.backend.domain.giftcard.controller;

import beachcombing.backend.domain.giftcard.dto.GiftcardFindAllResponse;
import beachcombing.backend.domain.giftcard.dto.PurchaseGiftcardRequest;
import beachcombing.backend.domain.giftcard.dto.PurchaseUpdateResponse;
import beachcombing.backend.domain.giftcard.dto.PurchaseFindAllResponse;
import beachcombing.backend.domain.giftcard.service.GiftcardService;
import beachcombing.backend.global.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giftcards")
@RequiredArgsConstructor
public class GiftcardController {

    private final GiftcardService giftcardService;

    // 카드 목록 조회
    @GetMapping("")
    public ResponseEntity<List<GiftcardFindAllResponse>> findAllGiftcard() {
        List<GiftcardFindAllResponse> giftcardListResponses = giftcardService.findAllGiftcard();
        return ResponseEntity.ok().body(giftcardListResponses);
    }

    // 카드 구매
    @PostMapping("/{giftcardId}/purchase")
    public ResponseEntity<PurchaseUpdateResponse> updatePurchase(@AuthenticationPrincipal PrincipalDetails userDetails, @PathVariable Long giftcardId) {
        PurchaseUpdateResponse giftcardPurchaseResponse = giftcardService.updatePurchase(PurchaseGiftcardRequest.builder()
                .memberId(userDetails.getMember().getId()).giftcardId(giftcardId).build());

        return ResponseEntity.ok().body(giftcardPurchaseResponse);
    }

    @GetMapping("/purchase")
    public ResponseEntity<List<PurchaseFindAllResponse>> findAllPurchase(@AuthenticationPrincipal PrincipalDetails userDetails) {
        List<PurchaseFindAllResponse> purchaseListResponses = giftcardService.findAllPurchase(userDetails.getMember().getId());

        return ResponseEntity.ok().body(purchaseListResponses);
    }
}
