package beachcombing.backend.domain.user.domain;

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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Embedded
    private Profile profile; // 기본 정보
    @Embedded
    private AuthInfo authInfo; // 로그인 정보

    @Builder
    public User( Profile profile, AuthInfo authInfo) {
        this.profile = profile;
        this.authInfo = authInfo;
    }

    public static User createUser(Profile profile,AuthInfo authInfo){
        return User.builder()
                .profile(profile)
                .authInfo(authInfo)
                .build();
    }
}
