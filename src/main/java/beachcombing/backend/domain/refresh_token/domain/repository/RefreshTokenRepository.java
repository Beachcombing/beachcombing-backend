package beachcombing.backend.domain.refresh_token.domain.repository;

import beachcombing.backend.domain.refresh_token.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByKeyLoginId(String keyLoginId);
}