package beachcombing.backend.domain.beach.controller;

import beachcombing.backend.domain.beach.dto.BeachFindMarkerResponse;
import beachcombing.backend.domain.beach.dto.BeachFindResponse;
import beachcombing.backend.domain.beach.service.BeachService;
import beachcombing.backend.domain.beach.dto.BeachFineMyMarkerResponse;
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
    public ResponseEntity<List<BeachFineMyMarkerResponse>> findMyMarkerBeach(@AuthenticationPrincipal PrincipalDetails userDetails){
        List<BeachFineMyMarkerResponse> response = beachService.findMyMarkerBeach(userDetails.getMember().getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 해번 상세 조회
    @GetMapping("{beachId}")
    public ResponseEntity<BeachFindResponse> findBeach(@PathVariable("beachId") Long beachId){
        BeachFindResponse response = beachService.findBeach(beachId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // (지도) 해변 위치 전체 조회
    @GetMapping("/location")
    public ResponseEntity<List<BeachFindMarkerResponse>> findMarkerBeach(){
        List<BeachFindMarkerResponse> response = beachService.findMarkerBeach();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 해변 근처 인증하기

}
