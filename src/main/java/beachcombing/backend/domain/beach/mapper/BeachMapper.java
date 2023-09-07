package beachcombing.backend.domain.beach.mapper;

import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.beach.dto.BeachFindOneResponse;
import beachcombing.backend.domain.beach.dto.BeachFineMarkerOneResponse;
import beachcombing.backend.domain.record.domain.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeachMapper {

    public BeachFineMarkerOneResponse toBeachFindMarkerOneResponse(Beach beach){
        return BeachFineMarkerOneResponse.builder()
                .id(beach.getId())
                .name(beach.getName())
                .lat(beach.getLocation().getLat().toString())
                .lng(beach.getLocation().getLng().toString())
                .build();
    }

    public BeachFindOneResponse toBeachFindOneResponse(Beach beach){
        return BeachFindOneResponse.builder()
                .beach(BeachFindOneResponse.BeachDto.from(beach))
                .build();

    }
    public BeachFindOneResponse toBeachFindOneResponse(Beach beach, Record record, String beforeImage, String afterImage){
        return BeachFindOneResponse.builder()
                .beach(BeachFindOneResponse.BeachDto.from(beach))
                .record(BeachFindOneResponse.RecordDto.from(record, beforeImage, afterImage))
                .member(BeachFindOneResponse.MemberDto.from(record.getMember()))
                .build();

    }



}
