package beachcombing.backend.domain.record.dto;

import lombok.Builder;

@Builder
public class RecordBeachMarkerResponse {
    public Long id;
    public String name;
    public String lat;
    public String lng;
}
