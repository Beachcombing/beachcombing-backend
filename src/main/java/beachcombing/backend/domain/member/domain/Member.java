package beachcombing.backend.domain.member.domain;

import beachcombing.backend.domain.auth.controller.dto.AuthGoogleLoginDto;
import beachcombing.backend.domain.common.domain.BaseEntity;
import beachcombing.backend.domain.common.domain.Provider;
import beachcombing.backend.domain.common.domain.Role;
import beachcombing.backend.global.util.ImageUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Builder
    public Member(Profile profile, AuthInfo authInfo, Integer totalPoint, Integer monthPoint, Integer purchasePoint,
                  Boolean profilePublic, Boolean tutorialCompleted) {
        this.profile = profile;
        this.authInfo = authInfo;
        this.totalPoint = totalPoint;
        this.monthPoint = monthPoint;
        this.purchasePoint = purchasePoint;
        this.profilePublic = profilePublic;
        this.tutorialCompleted = tutorialCompleted;
    }

    public static Member createMember(Profile profile, AuthInfo authInfo) {
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

    public static Member createMemberByGoogleLogin(AuthGoogleLoginDto googleLoginDto) {
        Profile profile = Profile.createProfile(googleLoginDto.email, googleLoginDto.name, googleLoginDto.picture,
                Role.MEMBER);
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

    //튜토리얼 완료 등록하기
    public void completeTutorial() {
        this.tutorialCompleted = true;
    }

    public Integer getRemainPoints() {
        return this.totalPoint - this.purchasePoint;
    }

    public boolean updateMemberPoint(int option) {
        int point = 0;

        // TODO: option을 enum으로 만들기
        switch (option) {
            case 0:
                point = 100;
                break;
            case 1:
                point = 30;
                break;
            case 2:
                point = 70;
                break;
            default:
                return false;
        }

        this.totalPoint += point;
        this.monthPoint += point;
        return true;
    }

    public String getNickname() {

        return profile.getNickname();
    }

    public String getImage() {

        return ImageUtil.processImage(retrieveImage());
    }

    private String retrieveImage() {

        return profile.getImage();
    }
}
