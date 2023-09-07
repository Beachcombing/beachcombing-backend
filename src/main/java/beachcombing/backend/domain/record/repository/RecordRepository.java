package beachcombing.backend.domain.record.repository;

import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.record.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findByMember(Member member);

}
