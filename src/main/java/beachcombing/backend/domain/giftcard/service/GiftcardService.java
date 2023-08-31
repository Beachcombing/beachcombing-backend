package beachcombing.backend.domain.giftcard.service;

import beachcombing.backend.domain.giftcard.domain.Giftcard;
import beachcombing.backend.domain.giftcard.dto.GiftcardListResponse;
import beachcombing.backend.domain.giftcard.repository.GiftcardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftcardService {

    private final GiftcardRepository giftcardRepository;

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
}
