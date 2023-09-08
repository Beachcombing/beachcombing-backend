package beachcombing.backend.domain.beach.controller.dto;

import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.record.domain.Record;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeachFindResponse {
    public BeachDto beach;
    public RecordDto record;
    public MemberDto member;

    @Builder
    public static class BeachDto {
        private Long id;
        private String name;

        public static BeachDto from(Beach beach) {

            return BeachDto.builder()
                    .id(beach.getId())
                    .name(beach.getName())
                    .build();
        }
    }

    @Builder
    public static class RecordDto {

        private Long id;
        private String nickname;
        @JsonFormat(pattern = "yy.MM.dd")
        private LocalDateTime date;
        private LocalTime duration;
        private Long distance;
        private String beforeImage;
        private String afterImage;

        public static RecordDto from(Record record, String beforeImage, String afterImage) {

            return RecordDto.builder()
                    .id(record.getId())
                    .nickname(record.getMember().getProfile().getNickname())
                    .date(record.getCreatedDate())
                    .duration(record.getDuration())
                    .distance(record.getDistance())
                    .beforeImage(beforeImage)
                    .afterImage(afterImage)
                    .build();
        }
    }


    @Builder
    public static class MemberDto {
        private Long id;
        private String nickname;

        public static MemberDto from(Member member) {
            return MemberDto.builder()
                    .id(member.getId())
                    .nickname(member.getProfile().getNickname())
                    .build();
        }
    }
}
