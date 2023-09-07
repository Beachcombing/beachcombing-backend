package beachcombing.backend.domain.record.service;

import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.beach.service.BeachService;
import beachcombing.backend.domain.member.service.MemberService;
import beachcombing.backend.domain.record.dto.RecordByBeachFindAllResponse;
import beachcombing.backend.domain.beach.repository.BeachRepository;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.repository.MemberRepository;
import beachcombing.backend.domain.record.domain.Record;
import beachcombing.backend.domain.record.dto.*;
import beachcombing.backend.domain.record.mapper.RecordMapper;
import beachcombing.backend.domain.record.repository.RecordRepository;
import beachcombing.backend.global.config.exception.CustomException;
import beachcombing.backend.global.config.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RecordService {
    private final RecordRepository recordRepository;

    //private final ImageService imageService;
    private final MemberService memberService;
    private final BeachService beachService;
    private final RecordMapper recordMapper;

    // 청소 기록하기
    public RecordSaveResponse saveRecord(Long memberId, RecordSaveRequest request){
        Member member = memberService.getMember(memberId);
        Beach beach = beachService.getBeach(request.beachId);

        // 이미지 업로드
        //String beforeUuid = imageService.uploadImage(request.beforeImage);
        // String afterUuid = imageService.uploadImage(request.afterImage);
        String beforeUuid = "beforeImage";
        String afterUuid = "afterImage";

        Record record = Record.createRecord(request.duration, request.distance, beforeUuid, afterUuid, member, beach);

        recordRepository.save(record);

        return recordMapper.toRecordIdResponse(record);
    }

    // 자신의 청소기록 목록 조회
    @Transactional(readOnly = true)
    public List<RecordFindAllResponse> findAllRecord(Long memberId) {
        Member member = memberService.getMember(memberId);

        List<RecordFindAllResponse> response = recordRepository.findByMember(member).stream()
                .map(record -> {
                    Boolean isWritten = (record.getFeed() != null);
                    //String beforeImageUrl = imageService.processImage(record.getBeforeImage());
                    //String afterImageUrl = imageService.processImage(record.getAfterImage());
                    String beforeImageUrl = "beforeImageUrl";
                    String afterImageUrl = "afterImageUrl";
                    return recordMapper.toRecordFindAllResponse(record, beforeImageUrl, afterImageUrl, isWritten);
                })
                .toList();
        return response;
    }

    // (지도) 특정 해변 청소 기록 목록 조회
    @Transactional(readOnly = true)
    public RecordByBeachFindAllResponse findAllRecordByBeach(Long memberId, Long beachId) {
        Member member = memberService.getMember(memberId);
        Beach beach = beachService.getBeach(beachId);

        List<RecordDto> recordDtoList = recordRepository.findByMemberAndBeachOrderByCreatedDateDesc(member, beach).stream()
                .map(record -> {
                            //String beforeImageUrl = imageService.processImage(record.getBeforeImage());
                            //String afterImageUrl = imageService.processImage(record.getAfterImage());
                            String beforeImageUrl = "";
                            String afterImageUrl = "";
                            return recordMapper.toRecordDto(record, beforeImageUrl, afterImageUrl);
                        }
                )
                .toList();

        return recordMapper.toRecordByBeachFindAllResponse(beach, recordDtoList);
    }

    public Record getLatestRecord(Beach beach){
        return recordRepository.findTopByBeachOrderByCreatedDateDesc(beach)
                .orElse(null);
    }

    public List<Record> getRecords(Member member){
        return recordRepository.findByMember(member);
    }

}
