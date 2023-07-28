package beachcombing.backend.domain.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@OnDelete(action = OnDeleteAction.CASCADE)
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Embedded
    private Profile profile; // 기본 정보
    @Embedded
    private AuthInfo authInfo; // 로그인 정보

    @Builder
    public Member(Profile profile, AuthInfo authInfo) {
        this.profile = profile;
        this.authInfo = authInfo;
    }

    public static Member createUser(Profile profile, AuthInfo authInfo){
        return Member.builder()
                .profile(profile)
                .authInfo(authInfo)
                .build();
    }
}
