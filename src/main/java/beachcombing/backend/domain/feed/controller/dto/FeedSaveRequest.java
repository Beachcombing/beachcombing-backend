package beachcombing.backend.domain.feed.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FeedSaveRequest {

    @NotNull
    private Long recordId;

    @Size(max=300)
    private String review;
}
