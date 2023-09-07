package beachcombing.backend.domain.record.mapper;


import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.record.domain.Record;
import beachcombing.backend.domain.record.dto.RecordBeachMarkerResponse;
import beachcombing.backend.domain.record.dto.RecordIdResponse;
import beachcombing.backend.domain.record.dto.RecordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RecordMapper {

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


}
