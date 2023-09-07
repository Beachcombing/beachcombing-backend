package beachcombing.backend.domain.beach.controller;

import beachcombing.backend.domain.beach.service.BeachService;
import beachcombing.backend.domain.beach.dto.BeachFineOneResponse;
import beachcombing.backend.global.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<List<BeachFineOneResponse>> findOneBeach(@AuthenticationPrincipal PrincipalDetails userDetails){
        List<BeachFineOneResponse> response = beachService.findOneBeach(userDetails.getMember().getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
