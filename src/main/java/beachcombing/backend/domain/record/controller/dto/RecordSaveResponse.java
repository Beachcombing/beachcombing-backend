package beachcombing.backend.domain.record.controller.dto;

import beachcombing.backend.domain.record.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordSaveResponse {
    public Long id;

    public static RecordSaveResponse from(Record record){
        return RecordSaveResponse.builder()
                .id(record.getId())
                .build();
    }

}
