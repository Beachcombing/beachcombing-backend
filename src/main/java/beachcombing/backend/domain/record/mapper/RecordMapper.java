package beachcombing.backend.domain.record.mapper;


import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.record.controller.dto.RecordByBeachFindAllResponse;
import beachcombing.backend.domain.record.controller.dto.RecordFindAllResponse;
import beachcombing.backend.domain.record.controller.dto.RecordSaveRequest;
import beachcombing.backend.domain.record.controller.dto.RecordSaveResponse;
import beachcombing.backend.domain.record.domain.Record;
import beachcombing.backend.domain.record.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class RecordMapper {

    public RecordSaveResponse toRecordIdResponse(Record record){
        return RecordSaveResponse.builder()
                .id(record.getId())
                .build();
    }

    public RecordByBeachFindAllResponse toRecordByBeachFindAllResponse(Beach beach, List<RecordByBeachFindAllResponse.RecordDto> recordList){
        return RecordByBeachFindAllResponse.builder()
                .beachName(beach.getName())
                .recordList(recordList)
                .build();
    }

    public RecordFindAllResponse toRecordFindAllResponse(Record record, String beforeImage, String afterImage, Boolean isWritten){
        return RecordFindAllResponse.builder()
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


    public Record toEntity(RecordSaveRequest request, String beforeImage, String afterImage, Member member, Beach beach){
        return Record.createRecord(request.duration, request.distance, beforeImage, afterImage, member, beach);
    }


}
