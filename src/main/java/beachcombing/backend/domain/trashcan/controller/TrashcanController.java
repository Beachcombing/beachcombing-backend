package beachcombing.backend.domain.trashcan.controller;

import beachcombing.backend.domain.trashcan.service.TrashcanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TrashcanController {

    private final TrashcanService trashcanService;

    // 쓰레기통 신고하기


    // 신고된 쓰레기통 목록 조회하기 (관리자)


    // 쓰레기통 인증하기 (관리자)


    // 인증된 쓰레기통 위치 조회 (지도)


}
