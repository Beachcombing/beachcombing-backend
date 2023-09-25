package beachcombing.backend.domain.member.domain.repository;

import beachcombing.backend.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByAuthInfoLoginId(String loginId);
    Boolean existsByProfileNickname(String nickname);



}
