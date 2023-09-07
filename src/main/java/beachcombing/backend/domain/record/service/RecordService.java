package beachcombing.backend.domain.record.service;

import beachcombing.backend.domain.beach.domain.Beach;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RecordService {
    private final RecordRepository recordRepository;

    //private final ImageService imageService;
    private final MemberRepository memberRepository;
    private final BeachRepository beachRepository;
    private final RecordMapper recordMapper;

    // 청소기록하기
    public RecordIdResponse saveRecord(Long memberId, RecordSaveRequest request){
        Member member = getMember(memberId);
        Beach beach = getBeach(request.beachId);
        checkExistsImage(request.beforeImage);
        checkExistsImage(request.afterImage);

        // 이미지 업로드
        //String beforeUuid = imageService.uploadImage(request.beforeImage);
        // String afterUuid = imageService.uploadImage(request.afterImage);
        String beforeUuid = "beforeImage";
        String afterUuid = "afterImage";

        Record record = Record.createRecord(request.duration, request.distance, beforeUuid, afterUuid, member, beach);

        recordRepository.save(record);

        return recordMapper.toRecordIdResponse(record);
    }

    // 청소기록 목록 조회
    public List<RecordResponse> getRecordList(Long memberId) {
        Member member = getMember(memberId);

        List<RecordResponse> response = recordRepository.findByMember(member).stream()
                .map(record -> {
                    Boolean isWritten = (record.getFeed() != null);
                    //String beforeImageUrl = imageService.processImage(record.getBeforeImage());
                    //String afterImageUrl = imageService.processImage(record.getAfterImage());
                    String beforeImageUrl = "beforeImageUrl";
                    String afterImageUrl = "afterImageUrl";
                    return recordMapper.toRecordResponse(record, beforeImageUrl, afterImageUrl, isWritten);
                })
                .collect(Collectors.toList());
        return response;
    }

    // 마이페이지 - (지도) 청소한 해변 조회
    public List<RecordBeachMarkerResponse> getMyBeachMarker(Long memberId) {
        Member member = getMember(memberId);
        List<RecordBeachMarkerResponse> response = recordRepository.findByMember(member).stream()
                .map(Record::getBeach)
                .map(recordMapper::toRecordBeachMarkerResponse)
                .toList();

        return response;
    }

    // 마이페이지 - (지도) 특정 위치 청소 기록 목록 조회
    public RecordListByBeachResponse getMyBeachRecord(Long memberId, Long beachId) {
        Member member = getMember(memberId);
        Beach beach = getBeach(beachId);

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

        return recordMapper.toRecordListByBeachResponse(beach, recordDtoList);
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

    // 예외 처리 - 이미지 있는지
    private void checkExistsImage(MultipartFile image) {
        if (image.isEmpty()) {
            throw new CustomException(ErrorCode.SHOULD_EXIST_IMAGE);
        }
    }

}
