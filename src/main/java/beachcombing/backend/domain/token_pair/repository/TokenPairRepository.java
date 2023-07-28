package beachcombing.backend.domain.token_pair.repository;

import beachcombing.backend.domain.token_pair.domain.TokenPair;
import beachcombing.backend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenPairRepository extends JpaRepository<TokenPair, Long> {
    Optional<TokenPair> findByUser(User user);
}