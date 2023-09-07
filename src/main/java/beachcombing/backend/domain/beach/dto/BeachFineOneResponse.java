package beachcombing.backend.domain.beach.dto;

import lombok.Builder;

@Builder
public class BeachFineOneResponse {
    public Long id;
    public String name;
    public String lat;
    public String lng;
}
