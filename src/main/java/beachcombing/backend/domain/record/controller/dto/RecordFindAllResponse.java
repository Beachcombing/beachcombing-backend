package beachcombing.backend.domain.record.controller.dto;

import beachcombing.backend.domain.record.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordFindAllResponse {

    public Long recordId;
    public LocalTime duration;
    public LocalDateTime date;
    public Long distance;
    public String beforeImage;
    public String afterImage;
    public Boolean isWritten;
    public Long beachId;
    public String beachName;

    public static RecordFindAllResponse of(Record record, String beforeImage, String afterImage, Boolean isWritten){
        return RecordFindAllResponse.builder()
                .recordId(record.getId())
                .duration(record.getDuration())
                .date(record.getCreatedDate())
                .distance(record.getDistance())
                .beforeImage(beforeImage)
                .afterImage(afterImage)
                .isWritten(isWritten)
                .beachId(record.getBeach().getId())
                .beachName(record.getBeach().getName())
                .build();
    }
}
