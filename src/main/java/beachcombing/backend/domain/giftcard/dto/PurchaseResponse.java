package beachcombing.backend.domain.giftcard.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PurchaseResponse {
    private Long id;
    private String name;
    private String location;
    private String image;
    private Integer cost;
}
