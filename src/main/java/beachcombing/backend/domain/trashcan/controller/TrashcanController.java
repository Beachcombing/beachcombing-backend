package beachcombing.backend.domain.trashcan.controller;

import beachcombing.backend.domain.common.controller.dto.IdResponse;
import beachcombing.backend.domain.trashcan.controller.dto.TrashcanFindAllResponse;
import beachcombing.backend.domain.trashcan.controller.dto.TrashcanReportRequest;
import beachcombing.backend.domain.trashcan.service.TrashcanService;
import beachcombing.backend.global.security.auth.PrincipalDetails;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TrashcanController {

    private final TrashcanService trashcanService;

    // 쓰레기통 신고하기
    @PostMapping("/trashcans/report")
    public ResponseEntity<IdResponse> reportTrashcan(@AuthenticationPrincipal PrincipalDetails userDetails,
                                                     @Valid TrashcanReportRequest request) throws IOException {

        Long trashcanId = trashcanService.reportTrashcan(userDetails.getMember().getId(), request);
        IdResponse response = IdResponse.from(trashcanId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    // 신고된 쓰레기통 목록 조회하기 (관리자)
    @GetMapping("/admin/trashcans/reported")
    public ResponseEntity<List<TrashcanFindAllResponse>> findAllReportedTrashcan() {

        List<TrashcanFindAllResponse> response = trashcanService.findAllReportedTrashcan();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 쓰레기통 인증하기 (관리자)
    @PatchMapping("/admin/trashcans/{trashcanId}/certify")
    public ResponseEntity<Void> certifyTrashcan(@PathVariable("trashcanId") Long trashcanId) {

        trashcanService.certifyTrashcan(trashcanId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 인증된 쓰레기통 위치 조회 (지도)
    @GetMapping("/trashcans/certified")
    public ResponseEntity<List<TrashcanFindAllResponse>> findAllCertifiedTrashcan() {

        List<TrashcanFindAllResponse> response = trashcanService.findAllCertifiedTrashcan();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
