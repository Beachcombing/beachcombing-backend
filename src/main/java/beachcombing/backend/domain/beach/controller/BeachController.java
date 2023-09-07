package beachcombing.backend.domain.beach.controller;

import beachcombing.backend.domain.beach.service.BeachService;
import beachcombing.backend.domain.beach.dto.BeachMarkerResponse;
import beachcombing.backend.domain.beach.dto.BeachRecordListResponse;
import beachcombing.backend.global.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("beaches")
public class BeachController {
    private final BeachService beachService;

    // (지도) 내가 청소한 해변 마커 조회
    @GetMapping("my")
    public ResponseEntity<List<BeachMarkerResponse>> getMyBeachMarker(@AuthenticationPrincipal PrincipalDetails userDetails){
        List<BeachMarkerResponse> response = beachService.getMyBeachMarker(userDetails.getMember().getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // (지도) 특정 해변 청소 기록 목록 조회
    @GetMapping("{beachId}/records")
    public ResponseEntity<BeachRecordListResponse> getMyBeachRecord(@AuthenticationPrincipal PrincipalDetails userDetails,
                                                                    @PathVariable("beachId") Long beachId) {

        BeachRecordListResponse response = beachService.getMyBeachRecord(userDetails.getMember().getId(), beachId);

        return ResponseEntity.status(HttpStatus.OK).body(response);




}
