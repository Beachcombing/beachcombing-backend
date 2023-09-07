package beachcombing.backend.domain.beach.mapper;

import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.beach.dto.BeachDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeachMapper {
    public BeachDto toBeachDto(Beach beach){
        return BeachDto.builder()
                .name(beach.getName())
                .build();
    }
}
