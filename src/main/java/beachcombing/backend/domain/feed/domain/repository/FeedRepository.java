package beachcombing.backend.domain.feed.domain.repository;

import beachcombing.backend.domain.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}
