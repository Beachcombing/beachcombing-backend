package beachcombing.backend.domain.feed.domain.repository;

import beachcombing.backend.domain.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    List<Feed> findAllByOrderByCreatedDateDesc();
}
