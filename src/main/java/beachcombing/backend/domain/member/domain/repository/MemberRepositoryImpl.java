package beachcombing.backend.domain.member.domain.repository;

import beachcombing.backend.domain.member.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MemberRepositoryImpl implements MemberRepositoryCustom{

    @Override
    public List<Member> findByTotalPointRanking(int pageSize, Long lastId, Integer lastPoint) {
        return null;
    }

    @Override
    public List<Member> findByMonthPointRanking(int pageSize, Long lastId, Integer lastPoint) {
        return null;
    }
}
