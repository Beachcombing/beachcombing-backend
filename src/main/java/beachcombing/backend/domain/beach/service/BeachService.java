package beachcombing.backend.domain.beach.service;

import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.beach.mapper.BeachMapper;
import beachcombing.backend.domain.beach.repository.BeachRepository;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.repository.MemberRepository;
import beachcombing.backend.domain.record.domain.Record;
import beachcombing.backend.domain.beach.dto.BeachFineOneResponse;
import beachcombing.backend.domain.record.mapper.RecordMapper;
import beachcombing.backend.domain.record.repository.RecordRepository;
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

    private final MemberRepository memberRepository;
    private final RecordRepository recordRepository;
    private final BeachRepository beachRepository;
    private final BeachMapper beachMapper;
    private final RecordMapper recordMapper;

    // (지도) 내가 청소한 해변 마커 조회
    @Transactional(readOnly = true)
    public List<BeachFineOneResponse> findOneBeach(Long memberId) {
        Member member = getMember(memberId);
        List<BeachFineOneResponse> response = recordRepository.findByMember(member).stream()
                .map(Record::getBeach)
                .map(beachMapper::toBeachFindOneResponse)
                .toList();

        return response;
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
