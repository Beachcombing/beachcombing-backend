package beachcombing.backend.domain.token_pair.repository;

import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.token_pair.domain.TokenPair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenPairRepository extends JpaRepository<TokenPair, Long> {
    Optional<TokenPair> findByMember(Member member);
}