package beachcombing.backend.domain.record.mapper;


import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.beach.mapper.BeachMapper;
import beachcombing.backend.domain.record.domain.Record;
import beachcombing.backend.domain.record.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class RecordMapper {
    private final BeachMapper beachMapper;

    public RecordIdResponse toRecordIdResponse(Record record){
        return RecordIdResponse.builder()
                .id(record.getId())
                .build();
    }

    public RecordResponse toRecordResponse(Record record, String beforeImage, String afterImage, Boolean isWritten){
        return RecordResponse.builder()
                .recordId(record.getId())
                .duration(record.getDuration())
                .date(record.getCreatedDate())
                .distance(record.getDistance())
                .beforeImage(beforeImage)
                .afterImage(afterImage)
                .isWritten(isWritten)
                .beachId(record.getBeach().getId())
                .beachName(record.getBeach().getName())
                .build();
    }

    public RecordBeachMarkerResponse toRecordBeachMarkerResponse(Beach beach){
        return RecordBeachMarkerResponse.builder()
                .id(beach.getId())
                .name(beach.getName())
                .lat(beach.getLocation().getLat().toString())
                .lng(beach.getLocation().getLng().toString())
                .build();
    }

    public RecordDto toRecordDto(Record record, String beforeImage, String afterImage) {
        return RecordDto.builder()
                .id(record.getId())
                .date(record.getCreatedDate())
                .duration(record.getDuration())
                .distance(record.getDistance())
                .beforeImage(beforeImage)
                .afterImage(afterImage)
                .build();
    }

    public RecordListByBeachResponse toRecordListByBeachResponse(Beach beach, List<RecordDto> recordDtoList){
        return RecordListByBeachResponse.builder()
                .beach(beachMapper.toBeachDto(beach))
                .recordList(recordDtoList)
                .build();
    }


}
