package beachcombing.backend.domain.record.controller;

import beachcombing.backend.domain.record.controller.dto.RecordByBeachFindAllResponse;
import beachcombing.backend.domain.record.controller.dto.RecordFindAllResponse;
import beachcombing.backend.domain.record.controller.dto.RecordSaveRequest;
import beachcombing.backend.domain.record.controller.dto.RecordSaveResponse;
import beachcombing.backend.domain.record.dto.*;
import beachcombing.backend.domain.record.service.RecordService;
import beachcombing.backend.global.security.auth.PrincipalDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("records")
public class RecordController {
    private final RecordService recordService;

    //청소 기록하기
    @PostMapping("")
    public ResponseEntity<RecordSaveResponse> saveRecord(@AuthenticationPrincipal PrincipalDetails userDetails,
                                                         @Valid RecordSaveRequest request){
        RecordSaveResponse response = recordService.saveRecord(userDetails.getMember().getId(), request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 자신의 청소기록 목록 조회
    @GetMapping("")
    public ResponseEntity<List<RecordFindAllResponse>> findAllRecord(@AuthenticationPrincipal PrincipalDetails userDetails){
        List<RecordFindAllResponse> response = recordService.findAllRecord(userDetails.getMember().getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // (지도) 특정 해변 청소 기록 목록 조회
    @GetMapping("beach")
    public ResponseEntity<RecordByBeachFindAllResponse> findAllRecordByBeach(@AuthenticationPrincipal PrincipalDetails userDetails,
                                                                             @RequestParam("id") Long beachId) {
        RecordByBeachFindAllResponse response = recordService.findAllRecordByBeach(userDetails.getMember().getId(), beachId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
