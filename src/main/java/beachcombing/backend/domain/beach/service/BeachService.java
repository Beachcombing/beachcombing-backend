package beachcombing.backend.domain.beach.service;

import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.beach.dto.BeachFindResponse;
import beachcombing.backend.domain.beach.mapper.BeachMapper;
import beachcombing.backend.domain.beach.repository.BeachRepository;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.repository.MemberRepository;
import beachcombing.backend.domain.member.service.MemberService;
import beachcombing.backend.domain.record.domain.Record;
import beachcombing.backend.domain.beach.dto.BeachFineMarkerResponse;
import beachcombing.backend.domain.record.repository.RecordRepository;
import beachcombing.backend.domain.record.service.RecordService;
import beachcombing.backend.global.config.exception.CustomException;
import beachcombing.backend.global.config.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<BeachFineMarkerResponse> findMarkerBeach(Long memberId) {
        Member member = getMember(memberId);
        List<BeachFineMarkerResponse> response = recordRepository.findByMember(member).stream()
                .map(Record::getBeach)
                .map(beachMapper::toBeachFindMarkerOneResponse)
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
