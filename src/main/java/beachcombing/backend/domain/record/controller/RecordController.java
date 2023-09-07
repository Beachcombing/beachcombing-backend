package beachcombing.backend.domain.record.controller;

import beachcombing.backend.domain.beach.dto.BeachMarkerResponse;
import beachcombing.backend.domain.record.dto.*;
import beachcombing.backend.domain.record.service.RecordService;
import beachcombing.backend.global.security.auth.PrincipalDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<RecordIdResponse> saveRecord(@AuthenticationPrincipal PrincipalDetails userDetails,
                                                       @Valid RecordSaveRequest request){

        RecordIdResponse response = recordService.saveRecord(userDetails.getMember().getId(), request);

        return ResponseEntity.ok().body(response);

    }

    // 자신의 청소기록 목록 조회
    @GetMapping("")
    public ResponseEntity<List<RecordResponse>> getRecordList(@AuthenticationPrincipal PrincipalDetails userDetails){
        List<RecordResponse> response = recordService.getRecordList(userDetails.getMember().getId());
        return ResponseEntity.ok().body(response);
    }



}
