package beachcombing.backend.domain.record.repository;

import beachcombing.backend.domain.record.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
