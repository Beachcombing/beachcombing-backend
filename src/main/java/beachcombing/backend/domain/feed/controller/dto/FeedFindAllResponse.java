package beachcombing.backend.domain.feed.controller.dto;


import beachcombing.backend.domain.feed.domain.Feed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedFindAllResponse {
    public Long id;
    public String review;
    public Long recordId;
    public Long memberId;
    public String nickname;
    public String beforeImage;
    public String afterImage;
    public String beachName;
    public Long likes;
    public Boolean preferred;
    public String memberImage;

    public static FeedFindAllResponse from(Feed feed, Long likes, Boolean preferred) {
        return FeedFindAllResponse.builder()
                .id(feed.getId())
                .review(feed.getReview())
                .recordId(feed.getRecord().getId())
                .memberId(feed.getRecord().getMember().getId())
                .nickname(feed.getRecord().getMember().getProfile().getNickname())
                .beforeImage(feed.getRecord().getBeforeImage())
                .afterImage(feed.getRecord().getAfterImage())
                .beachName(feed.getRecord().getBeach().getName())
                .likes(likes)
                .preferred(preferred)
                .memberImage(feed.getRecord().getMember().getProfile().getImage())
                .build();
    }
}
