package beachcombing.backend.domain.feed.controller.dto;

import beachcombing.backend.domain.feed.domain.Feed;
import beachcombing.backend.domain.record.controller.dto.RecordSaveResponse;
import beachcombing.backend.domain.record.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedSaveResponse {

    public Long id;

    public static FeedSaveResponse from(Feed feed){
        return FeedSaveResponse.builder()
                .id(feed.getId())
                .build();
    }
}
