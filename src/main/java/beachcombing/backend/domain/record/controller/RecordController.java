package beachcombing.backend.domain.record.controller;

import beachcombing.backend.domain.record.dto.RecordBeachMarkerResponse;
import beachcombing.backend.domain.record.dto.RecordIdResponse;
import beachcombing.backend.domain.record.dto.RecordResponse;
import beachcombing.backend.domain.record.dto.RecordSaveRequest;
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

    //청소기록하기
    @PostMapping("")
    public ResponseEntity<RecordIdResponse> saveRecord(@AuthenticationPrincipal PrincipalDetails userDetails,
                                                       @Valid RecordSaveRequest request){

        RecordIdResponse response = recordService.saveRecord(userDetails.getMember().getId(), request);

        return ResponseEntity.ok().body(response);

    }

    // 청소기록 목록 조회
    @GetMapping("")
    public ResponseEntity<List<RecordResponse>> getRecordList(@AuthenticationPrincipal PrincipalDetails userDetails){
        List<RecordResponse> response = recordService.getRecordList(userDetails.getMember().getId());
        return ResponseEntity.ok().body(response);
    }

    // 마이페이지 - (지도) 청소한 해변 조회
    @GetMapping("/location")
    public ResponseEntity<List<RecordBeachMarkerResponse>> getMyBeachMarker(@AuthenticationPrincipal PrincipalDetails userDetails){
        List<RecordBeachMarkerResponse> response = recordService.getMyBeachMarker(userDetails.getMember().getId());
        return ResponseEntity.ok().body(response);
    }



}
