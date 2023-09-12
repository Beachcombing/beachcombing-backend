package beachcombing.backend.domain.record.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordByBeachFindAllResponse {
    public String beachName;
    public List<RecordDto> recordList;

}
