package beachcombing.backend.domain.record.controller.dto;

import beachcombing.backend.domain.record.domain.Record;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordByBeachFindAllResponse {
    public String beachName;
    public List<RecordDto> recordList;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecordDto {
        public Long id;
        @JsonFormat(pattern = "yy.MM.dd")
        public LocalDateTime date;
        public LocalTime duration;
        public Long distance;
        public String beforeImage;
        public String afterImage;

        public static RecordDto from(Record record, String beforeImage, String afterImage) {

            return RecordByBeachFindAllResponse.RecordDto.builder()
                    .id(record.getId())
                    .date(record.getCreatedDate())
                    .duration(record.getDuration())
                    .distance(record.getDistance())
                    .beforeImage(beforeImage)
                    .afterImage(afterImage)
                    .build();
        }

    }


}
