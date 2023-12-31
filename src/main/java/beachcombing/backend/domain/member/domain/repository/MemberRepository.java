package beachcombing.backend.domain.member.domain.repository;

import beachcombing.backend.domain.member.domain.Member;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    Optional<Member> findByAuthInfoLoginId(String loginId);
    Boolean existsByProfileNickname(String nickname);

}
