package beachcombing.backend.domain.record.mapper;


import beachcombing.backend.domain.record.domain.Record;
import beachcombing.backend.domain.record.dto.RecordIdResponse;
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


}
