package beachcombing.backend.domain.record.dto;

import lombok.Builder;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
public class RecordResponse {

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
