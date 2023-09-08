package beachcombing.backend.domain.beach.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeachFineMarkerResponse {
    public Long id;
    public String name;
    public String lat;
    public String lng;
}
