package beachcombing.backend.domain.giftcard.controller;

import beachcombing.backend.domain.giftcard.dto.GiftcardListResponse;
import beachcombing.backend.domain.giftcard.service.GiftcardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/giftcards")
@RequiredArgsConstructor
public class GiftcardController {

    private final GiftcardService giftcardService;

    private
    @GetMapping("")
    ResponseEntity<List<GiftcardListResponse>> getGiftcardList() {
        List<GiftcardListResponse> giftcardListResponses = giftcardService.getGiftcardList();
        return ResponseEntity.ok().body(giftcardListResponses);
    }
}
