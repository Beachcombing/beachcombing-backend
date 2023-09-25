package beachcombing.backend.domain.feed.controller;

import beachcombing.backend.domain.feed.controller.dto.FeedSaveRequest;
import beachcombing.backend.domain.feed.controller.dto.FeedSaveResponse;
import beachcombing.backend.domain.feed.service.FeedService;
import beachcombing.backend.domain.member.controller.dto.MemberFindResponse;
import beachcombing.backend.domain.member.controller.dto.MemberUpdateRequest;
import beachcombing.backend.global.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feeds")
@Slf4j
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    //피드 기록하기
    @PostMapping("")
    public ResponseEntity<FeedSaveResponse> saveFeed(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody FeedSaveRequest feedSaveRequest){

        FeedSaveResponse response = feedService.saveFeed(principalDetails.getMember().getId(), feedSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //피드 삭제하기
    @DeleteMapping("{feedId}")
    public ResponseEntity<Void> deleteFeed(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable("feedId") Long feedId){

        feedService.deleteFeed(principalDetails.getMember().getId(), feedId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
