package beachcombing.backend.domain.beach.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.validator.constraints.Range;

@AllArgsConstructor
@Builder
public class BeachVerifyNearRequest {

    @NotNull
    @Range(min = -90, max = 90)
    public String lat;

    @NotNull
    @Range(min = -180, max = 180)
    public String lng;
}