package beachcombing.backend.domain.feed.domain;
import beachcombing.backend.domain.common.domain.BaseEntity;
import beachcombing.backend.domain.record.domain.Record;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feed extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long id;

    private String review; // 리뷰

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "feed")
    private Record record;

    public void setRecord(Record record) {
        this.record = record;
    }

    @Builder
    public Feed(String review, Record record) {
        this.review = review;
        this.record = record;
    }

    public static Feed createFeed(String review, Record record)
    {
        return Feed.builder()
                .review(review)
                .record(record)
                .build();
    }


}
