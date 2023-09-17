package beachcombing.backend.domain.record.domain.repository;

import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.record.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findByMember(Member member);
    List<Record> findByMemberAndBeachOrderByCreatedDateDesc(Member member, Beach beach);
    Optional<Record> findTopByBeachOrderByCreatedDateDesc (Beach beach);

}
