package beachcombing.backend.domain.trashcan.controller.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TrashcanReportRequest {

    @NotNull
    @Range(min = -90, max = 90)
    private BigDecimal lat;

    @NotNull
    @Range(min = -180, max = 180)
    private BigDecimal lng;

    @NotNull
    private MultipartFile image;

    @Builder
    private TrashcanReportRequest(BigDecimal lat, BigDecimal lng, MultipartFile image) {

        this.lat = lat;
        this.lng = lng;
        this.image = image;
    }
}
