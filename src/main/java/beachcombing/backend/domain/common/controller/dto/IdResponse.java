package beachcombing.backend.domain.common.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class IdResponse {

    private Long id;

    public static IdResponse from(Long id) {
        return IdResponse.builder()
                .id(id)
                .build();
    }
}
