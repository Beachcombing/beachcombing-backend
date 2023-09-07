package beachcombing.backend.domain.beach.repository;

import beachcombing.backend.domain.beach.domain.Beach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeachRepository extends JpaRepository<Beach, Long> {
}
