package beachcombing.backend.domain.record.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class RecordSaveRequest {
    @NotNull
    public Long beachId;
    @NotNull
    public LocalTime duration;
    @NotNull
    public Long distance;
    @NotNull
    public MultipartFile beforeImage;
    @NotNull
    public MultipartFile afterImage;
}
