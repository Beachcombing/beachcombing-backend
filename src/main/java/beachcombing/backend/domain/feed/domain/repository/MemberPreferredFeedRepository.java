package beachcombing.backend.domain.feed.domain.repository;

import beachcombing.backend.domain.feed.domain.Feed;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member_preferred_feed.domain.MemberPreferredFeed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPreferredFeedRepository extends JpaRepository<MemberPreferredFeed, Long> {
    Long countByFeed(Feed feed);
    Boolean existsByMemberAndFeed(Member member, Feed feed);
    void deleteByMemberAndFeed(Member member, Feed feed);
}
