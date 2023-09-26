package beachcombing.backend.domain.member.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FeedLikeResponse {
    private Long id;

    public static FeedLikeResponse from(Long id) {
        return FeedLikeResponse.builder()
                .id(id)
                .build();
    }
}
