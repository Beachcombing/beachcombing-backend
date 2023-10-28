package beachcombing.backend.domain.record.service;

import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.beach.domain.repository.BeachRepository;
import beachcombing.backend.domain.image.service.ImageService;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.domain.repository.MemberRepository;
import beachcombing.backend.domain.record.controller.dto.RecordByBeachFindAllResponse;
import beachcombing.backend.domain.record.controller.dto.RecordFindAllResponse;
import beachcombing.backend.domain.record.controller.dto.RecordSaveRequest;
import beachcombing.backend.domain.record.controller.dto.RecordSaveResponse;
import beachcombing.backend.domain.record.domain.Record;
import beachcombing.backend.domain.record.domain.repository.RecordRepository;
import beachcombing.backend.global.config.exception.CustomException;
import beachcombing.backend.global.config.exception.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RecordService {
    private final RecordRepository recordRepository;

    private final ImageService imageService;
    private final MemberRepository memberRepository;
    private final BeachRepository beachRepository;


    // 청소 기록하기
    public RecordSaveResponse saveRecord(Long memberId, RecordSaveRequest request) {
        Member member = getMember(memberId);
        Beach beach = getBeach(request.beachId);

        String beforeUuid = imageService.uploadImage(request.beforeImage);
        String afterUuid = imageService.uploadImage(request.afterImage);

        Record record = Record.createRecord(request.duration, request.distance, beforeUuid, afterUuid, member, beach);
        recordRepository.save(record);

        return RecordSaveResponse.from(record);
    }

    // 자신의 청소기록 목록 조회
    @Transactional(readOnly = true)
    public List<RecordFindAllResponse> findAllRecord(Long memberId) {
        Member member = getMember(memberId);

        List<RecordFindAllResponse> response = recordRepository.findByMember(member).stream()
                .map(record -> {
                    Boolean isWritten = (record.getFeed() != null);
                    //String beforeImageUrl = imageService.processImage(record.getBeforeImage());
                    //String afterImageUrl = imageService.processImage(record.getAfterImage());
                    String beforeImageUrl = "beforeImageUrl";
                    String afterImageUrl = "afterImageUrl";
                    return RecordFindAllResponse.of(record, beforeImageUrl, afterImageUrl, isWritten);
                })
                .toList();
        return response;
    }

    // (지도) 특정 해변 청소 기록 목록 조회
    @Transactional(readOnly = true)
    public RecordByBeachFindAllResponse findAllRecordByBeach(Long memberId, Long beachId) {
        Member member = getMember(memberId);
        Beach beach = getBeach(beachId);

        List<RecordByBeachFindAllResponse.RecordDto> recordDtoList = recordRepository.findByMemberAndBeachOrderByCreatedDateDesc(
                        member, beach).stream()
                .map(record -> {
                            //String beforeImageUrl = imageService.processImage(record.getBeforeImage());
                            //String afterImageUrl = imageService.processImage(record.getAfterImage());
                            String beforeImageUrl = "";
                            String afterImageUrl = "";
                            return RecordByBeachFindAllResponse.RecordDto.from(record, beforeImageUrl, afterImageUrl);
                        }
                )
                .toList();

        return RecordByBeachFindAllResponse.of(beach, recordDtoList);
    }

    // 예외 처리 - 존재하는 member 인가
    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
    }

    // 예외 처리 - 존재하는 beach 인가
    private Beach getBeach(Long beachId) {
        return beachRepository.findById(beachId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BEACH));
    }


}
