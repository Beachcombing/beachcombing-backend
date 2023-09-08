package beachcombing.backend.domain.giftcard.service;

import beachcombing.backend.domain.giftcard.domain.Giftcard;
import beachcombing.backend.domain.giftcard.dto.GiftcardFindAllResponse;
import beachcombing.backend.domain.giftcard.dto.PurchaseGiftcardRequest;
import beachcombing.backend.domain.giftcard.dto.PurchaseUpdateResponse;
import beachcombing.backend.domain.giftcard.dto.PurchaseFindAllResponse;
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

    public List<GiftcardFindAllResponse> findAllGiftcard() {
        List<GiftcardFindAllResponse> giftcardResponseList = giftcardRepository.findAll().stream()
                .map(giftcard -> GiftcardFindAllResponse.builder()
                        .id(giftcard.getId())
                        .name(giftcard.getStore().getName())
                        .location(giftcard.getStore().getLocation())
                        .image(giftcard.getStore().getImage())
                        .cost(giftcard.getCost())
                        .build())
                .collect(Collectors.toList());

        return giftcardResponseList;
    }

    public PurchaseUpdateResponse updatePurchase(PurchaseGiftcardRequest purchaseGiftcardRequest) {
        Member member = memberRepository.findById(purchaseGiftcardRequest.getMemberId())
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
        Giftcard giftcard = giftcardRepository.findById(purchaseGiftcardRequest.getGiftcardId())
                .orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_GIFTCARD));

        Purchase purchase = Purchase.builder()
                .member(member)
                .giftcard(giftcard)
                .build();

        purchaseRepository.save(purchase);

        return PurchaseUpdateResponse.builder().id(purchase.getId()).build();
    }

    public List<PurchaseFindAllResponse> findAllPurchase(Long memberId) {
        List<Giftcard> giftcardList = giftcardRepository.findBy(memberId);


        purchaseRepository.findByMemberId(memberId).stream()
                .map(Purchase::getGiftcard)
                .map(giftcard -> PurchaseFindAllResponse.builder()
                        .id(giftcard.getId())
                        .name(giftcard.getStore().getName())
                        .location(giftcard.getStore().getLocation())
                        .image(giftcard.getStore().getImage())
                        .cost(giftcard.getCost())
                        .build())
                .collect(Collectors.toList());
    }
}
