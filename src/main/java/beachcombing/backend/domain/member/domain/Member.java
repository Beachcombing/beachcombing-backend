package beachcombing.backend.domain.member.domain;

import beachcombing.backend.domain.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@OnDelete(action = OnDeleteAction.CASCADE)
@Builder
@AllArgsConstructor
@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    protected Long id;

    @Embedded
    private Profile profile; // 기본 정보
    @Embedded
    private AuthInfo authInfo; // 로그인 정보

    // 회원 추가 정보
    @Builder.Default
    private Integer totalPoint = 0; // 전체 포인트
    @Builder.Default
    private Integer monthPoint = 0; // 월간 포인트
    @Builder.Default
    private Integer purchasePoint = 0; // 구매 포인트
    @Builder.Default
    private Boolean profilePublic = true; // 프로필 공개 여부
    @Builder.Default
    private Boolean tutorialCompleted = false; // 튜토리얼 완료 여부

//    @OneToMany(mappedBy = "member")
//    private List<Record> records = new ArrayList<>();  // 청소 기록 리스트 (Record:Member=다:1)
//
//    @OneToMany(mappedBy = "member")
//    private List<Purchase> purchaseList = new ArrayList<>();  // 구매 기록 리스트 (Purchase:Member=다:1)

    public static Member createUser(Profile profile, AuthInfo authInfo){
        return Member.builder()
                .profile(profile)
                .authInfo(authInfo)
                .build();
    }
}
