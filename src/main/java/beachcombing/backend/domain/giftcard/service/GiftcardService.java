package beachcombing.backend.domain.giftcard.service;

import beachcombing.backend.domain.giftcard.domain.Giftcard;
import beachcombing.backend.domain.giftcard.dto.GiftcardListResponse;
import beachcombing.backend.domain.giftcard.dto.PurchaseGiftcardRequest;
import beachcombing.backend.domain.giftcard.dto.PurchaseGiftcardResponse;
import beachcombing.backend.domain.giftcard.repository.GiftcardRepository;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.repository.MemberRepository;
import beachcombing.backend.domain.purchase.domain.Purchase;
import beachcombing.backend.domain.purchase.repository.PurchaseRepository;
import beachcombing.backend.global.config.exception.CustomException;
import beachcombing.backend.global.config.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftcardService {

    private final GiftcardRepository giftcardRepository;
    private final PurchaseRepository purchaseRepository;
    private final MemberRepository memberRepository;

    public List<GiftcardListResponse> getGiftcardList() {
        return giftcardRepository.findAll().stream()
                .map(giftcard -> GiftcardListResponse.builder()
                        .id(giftcard.getId())
                        .name(giftcard.getStore().getName())
                        .location(giftcard.getStore().getLocation())
                        .image(giftcard.getStore().getImage())
                        .cost(giftcard.getCost())
                        .build())
                .collect(Collectors.toList());
    }

    public PurchaseGiftcardResponse purchaseGiftcard(PurchaseGiftcardRequest purchaseGiftcardRequest) {
        Member member = memberRepository.findById(purchaseGiftcardRequest.getMemberId())
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER));
        Giftcard giftcard = giftcardRepository.findById(purchaseGiftcardRequest.getGiftcardId())
                .orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_GIFTCARD));

        Purchase purchase = Purchase.builder()
                .member(member)
                .giftcard(giftcard)
                .build();

        purchaseRepository.save(purchase);

        return PurchaseGiftcardResponse.builder().id(purchase.getId()).build();
    }
}
