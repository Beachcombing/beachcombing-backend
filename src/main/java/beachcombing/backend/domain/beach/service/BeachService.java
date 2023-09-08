package beachcombing.backend.domain.beach.service;

import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.beach.controller.dto.BeachFindMarkerResponse;
import beachcombing.backend.domain.beach.controller.dto.BeachFindResponse;
import beachcombing.backend.domain.beach.controller.dto.BeachVerifyNearRequest;
import beachcombing.backend.domain.beach.mapper.BeachMapper;
import beachcombing.backend.domain.beach.domain.repository.BeachRepository;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.repository.MemberRepository;
import beachcombing.backend.domain.record.domain.Record;
import beachcombing.backend.domain.beach.controller.dto.BeachFineMyMarkerResponse;
import beachcombing.backend.domain.record.domain.repository.RecordRepository;
import beachcombing.backend.global.config.exception.CustomException;
import beachcombing.backend.global.config.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BeachService {
    private final BeachRepository beachRepository;
    private final BeachMapper beachMapper;
    private final MemberRepository memberRepository;
    private final RecordRepository recordRepository;

    // (지도) 내가 청소한 해변 마커 조회
    @Transactional(readOnly = true)
    public List<BeachFineMyMarkerResponse> findMyMarkerBeach(Long memberId) {
        Member member = getMember(memberId);
        List<BeachFineMyMarkerResponse> response = recordRepository.findByMember(member).stream()
                .map(Record::getBeach)
                .map(beachMapper::toBeachFindMyMarkerResponse)
                .toList();

        return response;
    }
    @Transactional(readOnly = true)
    public BeachFindResponse findBeach(Long beachId) {
        Beach beach = getBeach(beachId);
        Record record = recordRepository.findTopByBeachOrderByCreatedDateDesc(beach)
                .orElse(null);

        if(record == null || !record.getMember().getProfilePublic() ){
            return beachMapper.toBeachFindOneResponse(beach);
        }

        //String beforeImageUrl = imageService.processImage(record.getBeforeImage());
        //String afterImageUrl = imageService.processImage(record.getAfterImage());
        String beforeImageUrl = "test";
        String afterImageUrl = "test";

        return beachMapper.toBeachFindOneResponse(beach, record, beforeImageUrl, afterImageUrl);

    }

    @Transactional(readOnly = true)
    public List<BeachFindMarkerResponse> findMarkerBeach() {
        List<BeachFindMarkerResponse> response = beachRepository.findAll().stream()
                .map(beach -> {
                    String memberImageUrl = getLatestRecordMemberImage(beach);
                    return beachMapper.toBeachFindMarkerResponse(beach, memberImageUrl);
                })
                .toList();

        return response;
    }


    public void verifyNearBeach(Long beachId, BeachVerifyNearRequest request) {
        Beach beach = getBeach(beachId);
        beach.getRange();

    }

    // 최근 청소기록 유저 프로필이미지 url 조회
    private String getLatestRecordMemberImage(Beach beach){
        Optional<Record> latestRecord = recordRepository.findTopByBeachOrderByCreatedDateDesc(beach);

        if(latestRecord.isEmpty()) return "none";

        Record record = latestRecord.get();
        Member member = record.getMember();

        if(!member.getProfilePublic()) return "lock";

        //return imageService.processImage(member.getImage());
        return "imageUrl";
    }

    // 예외 처리 - 존재하는 beach 인가
    private Beach getBeach(Long beachId) {
        return beachRepository.findById(beachId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BEACH));
    }

    // 예외 처리 - 존재하는 Member 인가
    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
    }


}
