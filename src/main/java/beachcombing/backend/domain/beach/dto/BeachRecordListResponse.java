package beachcombing.backend.domain.beach.dto;

import beachcombing.backend.domain.record.dto.RecordDto;
import lombok.Builder;

import java.util.List;

@Builder
public class BeachRecordListResponse {
    public BeachDto beach;
    public List<RecordDto> recordList;

}