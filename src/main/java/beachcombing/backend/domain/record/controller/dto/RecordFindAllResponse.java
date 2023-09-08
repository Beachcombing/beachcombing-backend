package beachcombing.backend.domain.record.controller.dto;

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
}
