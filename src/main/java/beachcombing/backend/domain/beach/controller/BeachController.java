package beachcombing.backend.domain.beach.controller;

import beachcombing.backend.domain.beach.dto.BeachFindOneResponse;
import beachcombing.backend.domain.beach.service.BeachService;
import beachcombing.backend.domain.beach.dto.BeachFineMarkerOneResponse;
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
    public ResponseEntity<List<BeachFineMarkerOneResponse>> findMarkerOneBeach(@AuthenticationPrincipal PrincipalDetails userDetails){
        List<BeachFineMarkerOneResponse> response = beachService.findMarkerOneBeach(userDetails.getMember().getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 해번 상세 조회
    @GetMapping("{beachId}")
    public ResponseEntity<BeachFindOneResponse> findOneBeach(@PathVariable("beachId") Long beachId){
        BeachFindOneResponse response = beachService.findOneBeach(beachId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
