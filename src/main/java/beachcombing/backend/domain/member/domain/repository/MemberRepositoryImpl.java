package beachcombing.backend.domain.member.domain.repository;

import beachcombing.backend.domain.member.domain.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static beachcombing.backend.domain.member.domain.QMember.member;


public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Member> findByTotalPointRanking(int pageSize, Long lastId, Integer lastPoint) {
        System.out.println(whereClause(lastId, lastPoint, member.totalPoint));
        return queryFactory
                .select(member)
                .from(member)
                .where(whereClause(lastId, lastPoint, member.totalPoint))
                .orderBy(member.totalPoint.desc(), member.id.asc())
                .limit(pageSize+1)
                .fetch();
    }

    @Override
    public List<Member> findByMonthPointRanking(int pageSize, Long lastId, Integer lastPoint) {

        return queryFactory
                .select(member)
                .from(member)
                .where(whereClause(lastId, lastPoint, member.monthPoint))
                .orderBy(member.monthPoint.desc(), member.id.asc())
                .limit(pageSize+1)
                .fetch();
    }
    private BooleanExpression whereClause(Long lastId, Integer lastPoint, NumberPath<Integer> option) {

        if(lastId!= null && lastPoint != null )
        {
            return ((option.lt(lastPoint))
                    .or(option.eq(lastPoint).and(member.id.gt(lastId))));
        }
        return null;
    }


}
