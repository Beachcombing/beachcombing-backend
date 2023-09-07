package beachcombing.backend.domain.beach.mapper;

import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.beach.dto.BeachDto;
import beachcombing.backend.domain.beach.dto.BeachMarkerResponse;
import beachcombing.backend.domain.record.dto.RecordDto;
import beachcombing.backend.domain.beach.dto.BeachRecordListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BeachMapper {
    public BeachDto toBeachDto(Beach beach){
        return BeachDto.builder()
                .name(beach.getName())
                .build();
    }

    public BeachMarkerResponse toBeachMarkerResponse(Beach beach){
        return BeachMarkerResponse.builder()
                .id(beach.getId())
                .name(beach.getName())
                .lat(beach.getLocation().getLat().toString())
                .lng(beach.getLocation().getLng().toString())
                .build();
    }

    public BeachRecordListResponse toRecordListByBeachResponse(Beach beach, List<RecordDto> recordDtoList){
        return BeachRecordListResponse.builder()
                .beach(toBeachDto(beach))
                .recordList(recordDtoList)
                .build();
    }
}
