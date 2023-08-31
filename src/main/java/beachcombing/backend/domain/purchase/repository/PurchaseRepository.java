package beachcombing.backend.domain.purchase.repository;

import beachcombing.backend.domain.purchase.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
