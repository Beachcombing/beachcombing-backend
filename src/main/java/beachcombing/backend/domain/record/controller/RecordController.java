package beachcombing.backend.domain.record.controller;

import beachcombing.backend.domain.record.dto.RecordIdResponse;
import beachcombing.backend.domain.record.dto.RecordSaveRequest;
import beachcombing.backend.domain.record.service.RecordService;
import beachcombing.backend.global.security.auth.PrincipalDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("records")
public class RecordController {
    private final RecordService recordService;

    //청소기록하기
    @PostMapping("{beachId}")
    public ResponseEntity<RecordIdResponse> saveRecord(@AuthenticationPrincipal PrincipalDetails userDetails,
                                                       @Valid RecordSaveRequest request){

        RecordIdResponse response = recordService.saveRecord(userDetails.getMember().getId(), request);

        return ResponseEntity.ok().body(response);

    }
}
