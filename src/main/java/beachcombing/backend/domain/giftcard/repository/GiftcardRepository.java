package beachcombing.backend.domain.giftcard.repository;

import beachcombing.backend.domain.giftcard.domain.Giftcard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftcardRepository extends JpaRepository<Giftcard,Long> {

}
