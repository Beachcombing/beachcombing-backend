package beachcombing.backend.domain.beach.mapper;

import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.beach.dto.BeachFindResponse;
import beachcombing.backend.domain.beach.dto.BeachFineMarkerResponse;
import beachcombing.backend.domain.record.domain.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeachMapper {

    public BeachFineMarkerResponse toBeachFindMarkerOneResponse(Beach beach){
        return BeachFineMarkerResponse.builder()
                .id(beach.getId())
                .name(beach.getName())
                .lat(beach.getLocation().getLat().toString())
                .lng(beach.getLocation().getLng().toString())
                .build();
    }

    public BeachFindResponse toBeachFindOneResponse(Beach beach){
        return BeachFindResponse.builder()
                .beach(BeachFindResponse.BeachDto.from(beach))
                .build();

    }
    public BeachFindResponse toBeachFindOneResponse(Beach beach, Record record, String beforeImage, String afterImage){
        return BeachFindResponse.builder()
                .beach(BeachFindResponse.BeachDto.from(beach))
                .record(BeachFindResponse.RecordDto.from(record, beforeImage, afterImage))
                .member(BeachFindResponse.MemberDto.from(record.getMember()))
                .build();

    }



}
