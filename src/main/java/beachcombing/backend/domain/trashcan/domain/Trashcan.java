package beachcombing.backend.domain.trashcan.domain;

import beachcombing.backend.domain.common.domain.BaseEntity;
import beachcombing.backend.domain.common.domain.Location;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.global.util.ImageUtil;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trashcan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trashcan_id")
    private Long id;

    private String image;           // 쓰레기통 사진
    private Boolean isCertified;    // 인증 여부
    private String address;         // 쓰레기통 주소

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lat", column = @Column(nullable = false, precision = 10, scale = 8)),
            @AttributeOverride(name = "lng", column = @Column(nullable = false, precision = 11, scale = 8))
    })
    private Location location;      // 쓰레기통 좌표. TODO: column embeddable 타입 내에서 적용해도 바깥까지 적용되는지 확인해보기

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;          // 신고한 회원

    @Builder
    public Trashcan(String image, Boolean isCertified, String address, Location location, Member member) {

        this.image = image;
        this.isCertified = isCertified;
        this.address = address;
        this.location = location;
        this.member = member;
    }

    public static Trashcan createTrashcanByApi(Location location, String address) {

        return Trashcan.builder()
                .isCertified(true)
                .address(address)
                .location(location)
                .build();
    }

    public static Trashcan createTrashcanByMember(String image, Location location, Member member) {

        return Trashcan.builder()
                .image(image)
                .isCertified(false)
                .location(location)
                .member(member)
                .build();
    }

    public BigDecimal getLat() {

        return location.getLat();
    }

    public BigDecimal getLng() {

        return location.getLng();
    }

    public String getImage() {

        return ImageUtil.processImage(image);
    }

    public void certifyTrashcan(String address) {

        this.isCertified = true;
        this.address = address;
    }
}
