package beachcombing.backend.domain.trashcan.domain.repository;

import beachcombing.backend.domain.trashcan.domain.Trashcan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrashcanRepository extends JpaRepository<Trashcan, Long> {

    List<Trashcan> findByIsCertified(Boolean isCertified);
}