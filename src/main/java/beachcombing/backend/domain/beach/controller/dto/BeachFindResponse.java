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
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BeachDto {
        public Long id;
        public String name;

        public static BeachDto from(Beach beach) {

            return BeachDto.builder()
                    .id(beach.getId())
                    .name(beach.getName())
                    .build();
        }
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecordDto {

        public Long id;
        public String nickname;
        @JsonFormat(pattern = "yy.MM.dd")
        public LocalDateTime date;
        public LocalTime duration;
        public Long distance;
        public String beforeImage;
        public String afterImage;

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
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberDto {
        public Long id;
        public String nickname;

        public static MemberDto from(Member member) {
            return MemberDto.builder()
                    .id(member.getId())
                    .nickname(member.getProfile().getNickname())
                    .build();
        }
    }
}
