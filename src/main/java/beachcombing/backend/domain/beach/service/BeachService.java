package beachcombing.backend.domain.beach.service;

import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.beach.controller.dto.BeachFindMarkerResponse;
import beachcombing.backend.domain.beach.controller.dto.BeachFindResponse;
import beachcombing.backend.domain.beach.controller.dto.BeachVerifyNearRequest;
import beachcombing.backend.domain.beach.domain.repository.BeachRepository;
import beachcombing.backend.domain.beach.service.helper.RayCastingHelper;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.domain.repository.MemberRepository;
import beachcombing.backend.domain.record.domain.Record;
import beachcombing.backend.domain.beach.controller.dto.BeachFineMyMarkerResponse;
import beachcombing.backend.domain.record.domain.repository.RecordRepository;
import beachcombing.backend.global.config.exception.CustomException;
import beachcombing.backend.global.config.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BeachService {
    private final BeachRepository beachRepository;
    private final MemberRepository memberRepository;
    private final RecordRepository recordRepository;
    private final RayCastingHelper rayCastingHelper;

    // (지도) 내가 청소한 해변 마커 조회
    @Transactional(readOnly = true)
    public List<BeachFineMyMarkerResponse> findMyMarkerBeach(Long memberId) {
        Member member = getMember(memberId);
        List<BeachFineMyMarkerResponse> response = recordRepository.findByMember(member).stream()
                .map(Record::getBeach)
                .map(BeachFineMyMarkerResponse::from)
                .toList();

        return response;
    }

    // 해번 상세 조회
    @Transactional(readOnly = true)
    public BeachFindResponse findBeach(Long beachId) {
        Beach beach = getBeach(beachId);
        Record record = recordRepository.findTopByBeachOrderByCreatedDateDesc(beach)
                .orElse(null);

        if(record == null || !record.getMember().getProfilePublic() ){
            return BeachFindResponse.from(beach);
        }

        //String beforeImageUrl = imageService.processImage(record.getBeforeImage());
        //String afterImageUrl = imageService.processImage(record.getAfterImage());
        String beforeImageUrl = "test";
        String afterImageUrl = "test";

        return BeachFindResponse.of(beach, record, beforeImageUrl, afterImageUrl);

    }

    // (지도) 해변 위치 전체 조회
    @Transactional(readOnly = true)
    public List<BeachFindMarkerResponse> findMarkerBeach() {
        List<BeachFindMarkerResponse> response = beachRepository.findAll().stream()
                .map(beach -> {
                    String memberImageUrl = getLatestRecordMemberImage(beach);
                    return BeachFindMarkerResponse.of(beach, memberImageUrl);
                })
                .toList();

        return response;
    }

    // 해변 근처 인증하기
    public void verifyNearBeach(Long beachId, BeachVerifyNearRequest request) {
        Beach beach = getBeach(beachId);

        // 해변 범위 문자열 가공
        String tempRange = beach.getBeachRange()
                .replace("{", "")
                .replace(" ", "")
                .replaceAll(".$","");
        String[] beachRange = tempRange.split("},");

        // 각 좌표를 추출하여 리스트로 저장
        List<BigDecimal> xCoords = new ArrayList();
        List<BigDecimal> yCoords = new ArrayList();

        for(String vertex : beachRange){
            String xCoord = vertex.split(",")[0];
            String yCoord = vertex.split(",")[1];
            xCoords.add(new BigDecimal(xCoord));
            yCoords.add(new BigDecimal(yCoord));
        }

        // 주어진 좌표가 다각형 내부에 있는지 확인
        BigDecimal latitude = new BigDecimal(request.lat);
        BigDecimal longitude = new BigDecimal(request.lng);
        boolean isInside = rayCastingHelper.isInsidePolygon(xCoords, yCoords, latitude, longitude);

        // 다각형 내부에 없을 경우 예외 발생
        if (!isInside) {
            throw new CustomException(ErrorCode.NOT_NEAR_BEACH);
        }
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
