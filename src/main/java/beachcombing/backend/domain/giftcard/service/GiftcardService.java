package beachcombing.backend.domain.giftcard.service;

import beachcombing.backend.domain.giftcard.domain.Giftcard;
import beachcombing.backend.domain.giftcard.dto.GiftcardFindAllResponse;
import beachcombing.backend.domain.giftcard.dto.PurchaseUpdateResponse;
import beachcombing.backend.domain.giftcard.dto.PurchaseFindAllResponse;
import beachcombing.backend.domain.giftcard.mapper.GiftcardMapper;
import beachcombing.backend.domain.giftcard.repository.GiftcardRepository;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.repository.MemberRepository;
import beachcombing.backend.domain.purchase.domain.Purchase;
import beachcombing.backend.domain.purchase.mapper.PurchaseMapper;
import beachcombing.backend.domain.purchase.repository.PurchaseRepository;
import beachcombing.backend.global.config.exception.CustomException;
import beachcombing.backend.global.config.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftcardService {

    private final GiftcardRepository giftcardRepository;
    private final PurchaseRepository purchaseRepository;
    private final MemberRepository memberRepository;
    private final GiftcardMapper giftcardMapper;
    private final PurchaseMapper purchaseMapper;

    @Transactional(readOnly = true)
    public List<GiftcardFindAllResponse> findAllGiftcard() {
        List<Giftcard> giftcardList = giftcardRepository.findAll();

        return giftcardList.stream()
                .map(giftcardMapper::toGiftcardFindAllResponse)
                .toList();
    }

    public PurchaseUpdateResponse savePurchase(Long memberId, Long giftcardId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
        Giftcard giftcard = giftcardRepository.findById(giftcardId)
                .orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_GIFTCARD));

        Purchase purchase = Purchase.createPurchase(giftcard, member);

        purchaseRepository.save(purchase);

        return purchaseMapper.toPurchaseIdResponse(purchase);
    }

    @Transactional(readOnly = true)
    public List<PurchaseFindAllResponse> findAllPurchase(Long memberId) {

        List<Giftcard> giftcardList = purchaseRepository.findByMemberId(memberId).stream().map(Purchase::getGiftcard).toList();

        return giftcardList.stream()
                .map(giftcardMapper::toPurchaseFindAllResponse)
                .toList();
    }
}
