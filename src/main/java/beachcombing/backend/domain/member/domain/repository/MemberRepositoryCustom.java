package beachcombing.backend.domain.member.domain.repository;

import beachcombing.backend.domain.member.domain.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findByTotalPointRanking(int pageSize, Long lastId, Integer lastPoint);
    List<Member> findByMonthPointRanking(int pageSize, Long lastId, Integer lastPoint);
}
