package beachcombing.backend.domain.member_preferred_feed.domain;

import beachcombing.backend.domain.feed.domain.Feed;
import beachcombing.backend.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPreferredFeed {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_preferred_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public MemberPreferredFeed(Feed feed, Member member) {
        this.feed = feed;
        this.member = member;
    }

    public MemberPreferredFeed createMemberPreferredFeed(Feed feed, Member member){

        return MemberPreferredFeed.builder()
                .feed(feed)
                .member(member)
                .build();
    }
}
