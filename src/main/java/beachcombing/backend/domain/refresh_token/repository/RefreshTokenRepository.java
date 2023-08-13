package beachcombing.backend.domain.refresh_token.repository;

import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.refresh_token.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByMember(Member member);
}