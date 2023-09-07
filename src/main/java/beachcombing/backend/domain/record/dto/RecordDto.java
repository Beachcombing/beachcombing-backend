package beachcombing.backend.domain.record.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
public class RecordDto {
    public Long id;
    @JsonFormat(pattern = "yy.MM.dd")
    public LocalDateTime date;
    public LocalTime duration;
    public Long distance;
    public String beforeImage;
    public String afterImage;

}
