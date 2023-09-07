package beachcombing.backend.domain.beach.mapper;

import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.beach.dto.BeachFineOneResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeachMapper {

    public BeachFineOneResponse toBeachFindOneResponse(Beach beach){
        return BeachFineOneResponse.builder()
                .id(beach.getId())
                .name(beach.getName())
                .lat(beach.getLocation().getLat().toString())
                .lng(beach.getLocation().getLng().toString())
                .build();
    }

}
