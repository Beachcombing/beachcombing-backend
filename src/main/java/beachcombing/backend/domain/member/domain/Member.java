package beachcombing.backend.domain.member.domain;

import beachcombing.backend.domain.common.domain.Provider;
import beachcombing.backend.domain.auth.controller.dto.AuthGoogleLoginDto;
import beachcombing.backend.domain.common.domain.BaseEntity;
import beachcombing.backend.domain.common.domain.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@OnDelete(action = OnDeleteAction.CASCADE)
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
    private Integer totalPoint; // 전체 포인트
    
    private Integer monthPoint; // 월간 포인트

    private Integer purchasePoint; // 구매 포인트

    private Boolean profilePublic; // 프로필 공개 여부

    private Boolean tutorialCompleted; // 튜토리얼 완료 여부

//    @OneToMany(mappedBy = "member")
//    private List<Record> records = new ArrayList<>();  // 청소 기록 리스트 (Record:Member=다:1)
//
//    @OneToMany(mappedBy = "member")
//    private List<Purchase> purchaseList = new ArrayList<>();  // 구매 기록 리스트 (Purchase:Member=다:1)
    
    @Builder
    public Member(Profile profile, AuthInfo authInfo, Integer totalPoint, Integer monthPoint, Integer purchasePoint, Boolean profilePublic, Boolean tutorialCompleted) {
        this.profile = profile;
        this.authInfo = authInfo;
        this.totalPoint = totalPoint;
        this.monthPoint = monthPoint;
        this.purchasePoint = purchasePoint;
        this.profilePublic = profilePublic;
        this.tutorialCompleted = tutorialCompleted;
    }
  
    public static Member createMember(Profile profile, AuthInfo authInfo){
        return Member.builder()
                .profile(profile)
                .authInfo(authInfo)
                .totalPoint(0)
                .monthPoint(0)
                .purchasePoint(0)
                .profilePublic(true)
                .tutorialCompleted(false)
                .build();
    }

    public static Member createMemberByGoogleLogin(AuthGoogleLoginDto googleLoginDto){
        Profile profile = Profile.createProfile(googleLoginDto.email, googleLoginDto.name, googleLoginDto.picture, Role.MEMBER);
        AuthInfo authInfo = AuthInfo.createAuthInfo(googleLoginDto.sub, null, Provider.GOOGLE);

        return Member.builder()
                .profile(profile)
                .authInfo(authInfo)
                .totalPoint(0)
                .monthPoint(0)
                .purchasePoint(0)
                .profilePublic(true)
                .tutorialCompleted(false)
                .build();
    }

    public void updateProfilePublic(Boolean profilePublic) {
        this.profilePublic = profilePublic;
    }

    public boolean updateMemberPoint(int option){
        int point = 0;

        switch (option){
            case 0:
                point = 100;
                break;
            case 1:
                point = 30;
                break;
            case 2:
                point= 70;
                break;
            default:
                return false;
        }

        this.totalPoint += point;
        this.monthPoint += point;
        return true;

    }
}
