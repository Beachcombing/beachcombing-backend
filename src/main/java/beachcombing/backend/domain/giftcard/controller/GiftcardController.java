package beachcombing.backend.domain.giftcard.controller;

import beachcombing.backend.domain.giftcard.dto.GiftcardListResponse;
import beachcombing.backend.domain.giftcard.dto.PurchaseGiftcardRequest;
import beachcombing.backend.domain.giftcard.dto.PurchaseGiftcardResponse;
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
    public ResponseEntity<List<GiftcardListResponse>> getGiftcardList() {
        List<GiftcardListResponse> giftcardListResponses = giftcardService.getGiftcardList();
        return ResponseEntity.ok().body(giftcardListResponses);
    }

    // 카드 구매
    @PostMapping("/{giftcardId}/purchase")
    public ResponseEntity<PurchaseGiftcardResponse> purchaseGiftCard(@PathVariable Long giftcardId, @AuthenticationPrincipal PrincipalDetails userDetails) {
        PurchaseGiftcardResponse giftcardPurchaseResponse = giftcardService.purchaseGiftcard(PurchaseGiftcardRequest.builder()
                .memberId(userDetails.getMember().getId()).giftcardId(giftcardId).build());

        return ResponseEntity.ok().body(giftcardPurchaseResponse);
    }
}
