package beachcombing.backend.domain.member.controller;

import beachcombing.backend.domain.member.controller.dto.MemberFindRemainPointsResponse;
import beachcombing.backend.domain.member.controller.dto.MemberFindResponse;
import beachcombing.backend.domain.member.controller.dto.MemberTutorialSaveResponse;
import beachcombing.backend.domain.member.controller.dto.MemberUpdateRequest;
import beachcombing.backend.domain.member.controller.dto.MemberRankingAllResponse;
import beachcombing.backend.domain.member.controller.dto.NotificationFindResponse;
import beachcombing.backend.domain.member.service.MemberService;
import beachcombing.backend.global.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 내 정보 조회하기
    @GetMapping("")
    public ResponseEntity<MemberFindResponse> findMember(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        MemberFindResponse response = memberService.findMember(principalDetails.getMember().getId());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //회원 정보 수정하기
    @PatchMapping("")
    public ResponseEntity<Void> updateMember(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody MemberUpdateRequest memberUpdateRequest, @RequestParam(name = "is-changed") Boolean isChanged ) {

        memberService.updateMember(principalDetails.getMember().getId(), memberUpdateRequest, isChanged);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    //닉네임 중복 확인하기
    @GetMapping("nickname-check")
    public ResponseEntity<Void> checkNickname(@RequestParam(name = "nickname") String nickName) {

        memberService.checkNickname(nickName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //프로필 공개여부 설정하기
    @PatchMapping("profile-public")
    public ResponseEntity<Void> updateMemberProfilePublic(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam(name = "isPublic") Boolean profilePublic){

        memberService.updateMemberProfilePublic(principalDetails.getMember().getId(), profilePublic);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //회원 튜토리얼 완료여부 설정하기
    @PatchMapping("tutorial")
    public ResponseEntity<MemberTutorialSaveResponse> completeTutorial(@AuthenticationPrincipal PrincipalDetails principalDetails){

        MemberTutorialSaveResponse response = memberService.completeTutorial(principalDetails.getMember().getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //잔여포인트 조회하기

    @GetMapping("point")
    public ResponseEntity<MemberFindRemainPointsResponse> findRemainPoints(@AuthenticationPrincipal PrincipalDetails principalDetails)
    {
        MemberFindRemainPointsResponse response = memberService.findRemainPoints(principalDetails.getMember().getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //랭킹 조회하기
    @GetMapping("ranking")
    public ResponseEntity<MemberRankingAllResponse> getRankingList(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam(name="range") String range, @RequestParam(required = false) int pageSize, @RequestParam(required = false) Long lastId, @RequestParam(required = false) Integer lastPoint){
        MemberRankingAllResponse response = memberService.getRankingList(range, pageSize, lastId, lastPoint);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //회원 탈퇴하기
    @DeleteMapping("")
    public ResponseEntity<Void> deleteMember(@AuthenticationPrincipal PrincipalDetails principalDetails){

        memberService.deleteMember(principalDetails.getMember().getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 포인트 받기
    @PatchMapping("point")
    public ResponseEntity<Void> updateMemberPoint(@AuthenticationPrincipal PrincipalDetails userDetails,
                                                  @RequestParam("option") int option){
        memberService.updateMemberPoint(userDetails.getMember().getId(),option);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 알림 목록 조회
    @GetMapping("notification")
    public ResponseEntity<List<NotificationFindResponse>> findNotification(@AuthenticationPrincipal PrincipalDetails userDetails){
        List<NotificationFindResponse> response = memberService.findNotification(userDetails.getMember().getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
