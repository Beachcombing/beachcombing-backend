package beachcombing.backend.domain.trashcan.domain;

import beachcombing.backend.domain.common.domain.Location;
import beachcombing.backend.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trashcan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trashcan_id")
    private Long id;

    private String image; // 쓰레기통 사진
    private Boolean isCertified; // 인증 여부

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lng", column = @Column(nullable = false, precision = 10, scale = 8)),
            @AttributeOverride(name = "lng", column = @Column(nullable = false, precision = 11, scale = 8))
    })
    private Location location; // 쓰레기통 좌표. TODO: column embeddable 타입 내에서 적용해도 바깥까지 적용되는지 확인해보기

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 신고한 회원

    @Builder
    public Trashcan(String image, Boolean isCertified, Location location, Member member) {

        this.image = image;
        this.isCertified = isCertified;
        this.location = location;
        this.member = member;
    }

    public static Trashcan createTrashcan(String image, Boolean isCertified, Location location, Member member) {

        return Trashcan.builder()
                .image(image)
                .isCertified(isCertified)
                .location(location)
                .member(member)
                .build();
    }
}
