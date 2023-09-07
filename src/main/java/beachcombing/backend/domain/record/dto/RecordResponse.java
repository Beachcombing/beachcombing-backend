package beachcombing.backend.domain.record.dto;

import lombok.Builder;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
public class RecordResponse {

    private Long recordId;
    private LocalTime duration;
    private LocalDateTime date;
    private Long distance;
    private String beforeImage;
    private String afterImage;
    private Boolean isWritten;
    private Long beachId;
    private String beachName;
}
