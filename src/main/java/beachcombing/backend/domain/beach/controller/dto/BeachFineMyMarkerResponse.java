package beachcombing.backend.domain.beach.controller.dto;

import beachcombing.backend.domain.beach.domain.Beach;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeachFineMyMarkerResponse {
    public Long id;
    public String name;
    public String lat;
    public String lng;

    public static BeachFineMyMarkerResponse from(Beach beach){
        return BeachFineMyMarkerResponse.builder()
                .id(beach.getId())
                .name(beach.getName())
                .lat(beach.getLocation().getLat().toString())
                .lng(beach.getLocation().getLng().toString())
                .build();
    }
}
