package beachcombing.backend.domain.member.controller;

import beachcombing.backend.domain.member.dto.MemberFindOneResponse;
import beachcombing.backend.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 내 정보 조회하기
    @GetMapping("")
    public ResponseEntity<MemberFindOneResponse> findUser() { // TODO: 추후에 인증 정보 받게 수정할 예정

        MemberFindOneResponse response = memberService.findUser(15L);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}