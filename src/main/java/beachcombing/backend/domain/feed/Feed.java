package beachcombing.backend.domain.feed;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feed {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long id;

    private String review; // 리뷰

    @Builder
    public Feed(String review) {
        this.review = review;
    }

    public Feed createFeed(String review)
    {
        return Feed.builder()
                .review(review)
                .build();
    }

}
