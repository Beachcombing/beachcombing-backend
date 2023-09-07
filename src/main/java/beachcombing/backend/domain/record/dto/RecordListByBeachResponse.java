package beachcombing.backend.domain.record.dto;

import beachcombing.backend.domain.beach.dto.BeachDto;
import lombok.Builder;

import java.util.List;

@Builder
public class RecordListByBeachResponse {
    public BeachDto beach;
    public List<RecordDto> recordList;

}
