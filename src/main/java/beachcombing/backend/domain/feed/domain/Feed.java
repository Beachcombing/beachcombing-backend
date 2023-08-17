package beachcombing.backend.domain.feed.domain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feed {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long id;

    private String review;

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
