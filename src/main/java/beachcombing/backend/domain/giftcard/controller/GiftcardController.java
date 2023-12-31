package beachcombing.backend.domain.giftcard.controller;

import beachcombing.backend.domain.giftcard.dto.GiftcardFindAllResponse;
import beachcombing.backend.domain.giftcard.dto.PurchaseSaveResponse;
import beachcombing.backend.domain.giftcard.dto.PurchaseFindAllResponse;
import beachcombing.backend.domain.giftcard.service.GiftcardService;
import beachcombing.backend.global.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        List<GiftcardFindAllResponse> response = giftcardService.findAllGiftcard();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 카드 구매
    @PostMapping("/{giftcardId}/purchase")
    public ResponseEntity<PurchaseSaveResponse> updatePurchase(@AuthenticationPrincipal PrincipalDetails userDetails, @PathVariable Long giftcardId) {
        PurchaseSaveResponse response = giftcardService.savePurchase(userDetails.getMember().getId(), giftcardId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/purchase")
    public ResponseEntity<List<PurchaseFindAllResponse>> findAllPurchase(@AuthenticationPrincipal PrincipalDetails userDetails) {
        List<PurchaseFindAllResponse> response = giftcardService.findAllPurchase(userDetails.getMember().getId());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
