package beachcombing.backend.domain.trashcan.repository;

import beachcombing.backend.domain.trashcan.domain.Trashcan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrashcanRepository extends JpaRepository<Trashcan, Long> {

}