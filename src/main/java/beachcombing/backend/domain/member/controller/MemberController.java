package beachcombing.backend.domain.member.controller;

import beachcombing.backend.domain.member.dto.MemberUpdateOneRequest;
import beachcombing.backend.domain.member.dto.MemberFindOneResponse;
import beachcombing.backend.domain.member.service.MemberService;
import beachcombing.backend.global.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 내 정보 조회하기
    @GetMapping("")
    public ResponseEntity<MemberFindOneResponse> findMember(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        MemberFindOneResponse response = memberService.findMember(principalDetails.getMember().getId());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //회원 정보 수정하기
    @PatchMapping("")
    public ResponseEntity<Void> updateMember(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody MemberUpdateOneRequest memberUpdateOneRequest, @RequestParam(name = "is-changed") Boolean isChanged ) {

        memberService.updateMember(principalDetails.getMember().getId(), memberUpdateOneRequest, isChanged);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    //닉네임 중복 확인하기
    @GetMapping("nickname-check")
    public ResponseEntity<Void> checkNickname(@RequestParam(name = "nickname") String nickName) {

        memberService.checkNickname(nickName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //프로필 공개여부 설정하기
    @PatchMapping("profile-public")
    public ResponseEntity<Void> updateProfilePublic(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam(name = "isPublic") Boolean profilePublic){

        memberService.updateProfilePublic(principalDetails.getMember().getId(), profilePublic);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    }
