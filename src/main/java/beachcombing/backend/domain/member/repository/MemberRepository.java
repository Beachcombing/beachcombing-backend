package beachcombing.backend.domain.member.repository;

import beachcombing.backend.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByAuthInfoLoginId(String loginId);



}
