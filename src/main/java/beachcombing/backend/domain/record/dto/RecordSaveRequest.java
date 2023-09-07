package beachcombing.backend.domain.record.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
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
