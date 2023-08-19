package beachcombing.backend.domain.record.domain;

import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long id;

    private LocalTime duration; // 청소 시간 -> 00:00:00 (시, 분, 초)
    private Long distance; // 청소 거리
    private String beforeImage; // 청소 전 사진
    private String afterImage; // 청소 후 사진

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 청소한 회원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beach_id")
    private Beach beach; // 청소한 해변

    @Builder
    public Record(LocalTime duration, Long distance, String beforeImage, String afterImage, Member member, Beach beach) {

        this.duration = duration;
        this.distance = distance;
        this.beforeImage = beforeImage;
        this.afterImage = afterImage;
        this.member = member;
        this.beach = beach;
    }

    public static Record createRecord(LocalTime duration, Long distance, String beforeImage, String afterImage, Member member, Beach beach) {

        return Record.builder()
                .duration(duration)
                .distance(distance)
                .beforeImage(beforeImage)
                .afterImage(afterImage)
                .member(member)
                .beach(beach)
                .build();
    }
}
