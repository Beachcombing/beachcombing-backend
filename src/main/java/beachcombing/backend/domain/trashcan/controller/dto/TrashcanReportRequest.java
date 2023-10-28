package beachcombing.backend.domain.trashcan.controller.dto;

import beachcombing.backend.domain.common.domain.Location;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrashcanReportRequest {

    @NotNull
    @Range(min = -90, max = 90)
    private BigDecimal lat;

    @NotNull
    @Range(min = -180, max = 180)
    private BigDecimal lng;

    @NotNull
    private MultipartFile image;

    public Location toLocation() {
        return Location.builder()
                .lat(lat)
                .lng(lng)
                .build();
    }
}
