package beachcombing.backend.domain.feed;

import beachcombing.backend.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
public class MemberPreferredFeed {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_preferred_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
