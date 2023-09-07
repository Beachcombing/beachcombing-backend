package beachcombing.backend.domain.beach.dto;

import beachcombing.backend.domain.record.dto.RecordDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordByBeachFindAllResponse {
    public BeachDto beach;
    public List<RecordDto> recordList;

}
