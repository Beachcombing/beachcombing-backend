package beachcombing.backend.domain.giftcard.service;

import beachcombing.backend.domain.giftcard.dto.GiftcardFindAllResponse;
import beachcombing.backend.domain.giftcard.dto.PurchaseGiftcardRequest;
import beachcombing.backend.domain.giftcard.dto.PurchaseUpdateResponse;
import beachcombing.backend.domain.giftcard.dto.PurchaseFindAllResponse;
import beachcombing.backend.domain.giftcard.repository.GiftcardRepository;
import beachcombing.backend.domain.member.repository.MemberRepository;
import beachcombing.backend.domain.purchase.repository.PurchaseRepository;
import beachcombing.backend.global.config.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GiftcardServiceTest {

    @Autowired
    GiftcardService giftcardService;
    @Autowired
    GiftcardRepository giftcardRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("기프트카드 목록 조회")
    void getGiftcardList() {
        List<GiftcardFindAllResponse> giftcardListResponses = giftcardService.findAllGiftcard();
        assertThat(giftcardListResponses).extracting(GiftcardFindAllResponse::getName).contains("일산그린");
        System.out.println("0 번째 기프트 카드 스토어 이름 = " + giftcardListResponses.get(0).getName());
    }

    @Test
    @DisplayName("기프트카드 구매")
    void purchaseGiftcard() {
        // given
        PurchaseGiftcardRequest purchasegiftcardRequest = PurchaseGiftcardRequest.builder().memberId(7L).giftcardId(1L).build();

        // when
        PurchaseUpdateResponse purchaseGiftcardResponse = giftcardService.updatePurchase(purchasegiftcardRequest);

        // then
        assertThat(purchaseGiftcardResponse.getId()).isEqualTo(7);
    }

    @Test
    @DisplayName("존재하지 않는 기프트카드")
    void giftcardNotFoundError() {
        // given
        PurchaseGiftcardRequest purchasegiftcardRequest = PurchaseGiftcardRequest.builder().memberId(7L).giftcardId(2L).build();

        // then
        Assertions.assertThrows(CustomException.class, () -> giftcardService.updatePurchase(purchasegiftcardRequest));
    }

    @Test
    @DisplayName("구매한 기프트 카드 목록 조회")
    void getPurchaseList() {
        List<PurchaseFindAllResponse> purchaseListResponses =  giftcardService.findAllPurchase(7L);

        assertThat(purchaseListResponses.size()).isEqualTo(2);
    }
}