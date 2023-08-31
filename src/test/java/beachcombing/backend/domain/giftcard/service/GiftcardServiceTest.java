package beachcombing.backend.domain.giftcard.service;

import beachcombing.backend.domain.giftcard.dto.GiftcardListResponse;
import beachcombing.backend.domain.giftcard.repository.GiftcardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GiftcardServiceTest {

    @Autowired
    GiftcardService giftcardService;
    @Autowired
    GiftcardRepository giftcardRepository;

    @Test
    @DisplayName("기프트카드 목록 조회")
    void getGiftcardList() {
        List<GiftcardListResponse> giftcardListResponses = giftcardService.getGiftcardList();
        assertThat(giftcardListResponses).extracting(GiftcardListResponse::getName).contains("일산그린");
        System.out.println("0 번째 기프트 카드 스토어 이름 = " + giftcardListResponses.get(0).getName());
    }
}