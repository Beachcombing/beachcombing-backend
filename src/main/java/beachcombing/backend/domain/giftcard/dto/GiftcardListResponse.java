package beachcombing.backend.domain.giftcard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class GiftcardListResponse {
    private Long id;
    private String name;
    private String location;
    private String image;
    private Integer cost;
}
