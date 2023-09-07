package beachcombing.backend.domain.beach.dto;

import lombok.Builder;

@Builder
public class BeachMarkerResponse {
    public Long id;
    public String name;
    public String lat;
    public String lng;
}
