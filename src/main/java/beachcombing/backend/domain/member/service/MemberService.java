package beachcombing.backend.domain.member.service;


import beachcombing.backend.domain.feed.domain.Feed;
import beachcombing.backend.domain.feed.domain.repository.FeedRepository;
import beachcombing.backend.domain.feed.domain.repository.MemberPreferredFeedRepository;
import beachcombing.backend.domain.member.controller.dto.*;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.event.MemberEvent;
import beachcombing.backend.domain.member.event.NotificationCode;
import beachcombing.backend.domain.member.mapper.MemberMapper;
import beachcombing.backend.domain.member.domain.repository.MemberRepository;
import beachcombing.backend.domain.member_preferred_feed.domain.MemberPreferredFeed;
import beachcombing.backend.domain.notification.domain.Notification;
import beachcombing.backend.domain.notification.repository.NotificationRepository;
import beachcombing.backend.global.config.exception.CustomException;
import beachcombing.backend.global.config.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final NotificationRepository notificationRepository;
    private final FeedRepository feedRepository;
    private final MemberPreferredFeedRepository memberPreferredFeedRepository;

    private final ApplicationEventPublisher eventPublisher;

    //회원 정보 조회
    @Transactional(readOnly = true)
    public MemberFindResponse findMember(long memberId) {

        Member findMember = getMember(memberId);
        MemberFindResponse response = MemberFindResponse.from(findMember);

        return response;
    }

    //회원 정보 수정
    public void updateMember(long memberId, MemberUpdateRequest request, Boolean isChanged) {

        Member findMember = getMember(memberId);
        checkNickname(request.getNickname());
        findMember.getProfile().updateNicknameAndImage(request, isChanged);

    }

    //중복 닉네임 검증
    @Transactional(readOnly = true)
    public void checkNickname(String nickname) {

        Boolean nicknameCheck = memberRepository.existsByProfileNickname(nickname);
        if(nicknameCheck) {
            throw new CustomException(ErrorCode.EXIST_USER_NICKNAME);
        }
    }

    //프로필 공개여부 변경하기
    public void updateMemberProfilePublic(Long memberId, Boolean profilePublic) {

        Member findMember = getMember(memberId);
        findMember.updateProfilePublic(profilePublic);
    }

    //탈퇴하기
    public void deleteMember(Long memberId) {
        Member findMember = getMember(memberId);
        memberRepository.delete(findMember);
    }

    public void updateMemberPoint(Long memberId, int option) {
        Member member = getMember(memberId);

        // 옵션값이 0,1,2 가 아닐 때 에러처리
        if(!member.updateMemberPoint(option)){
            throw new CustomException(ErrorCode.BAD_REQUEST_OPTION_VALUE);
        }

        // 알림 생성
        if(option == 0){ // 기존에 등록된 쓰레기 통
            eventPublisher.publishEvent(MemberEvent.createMemberEvent(member, NotificationCode.CLEANING_AND_TRASH_DISPOSAL));
        }else if(option == 1){ // 미등록 쓰레기 통
            eventPublisher.publishEvent(MemberEvent.createMemberEvent(member, NotificationCode.CLEANING_WITHOUT_TRASH_DISPOSAL));
        }

    }
    @Transactional(readOnly = true)
    public List<NotificationFindResponse> findNotification(Long memberId) {
        Member member = getMember(memberId);

        List<NotificationFindResponse> response = notificationRepository.findByMember(member).stream()
                .map(NotificationFindResponse::from)
                .toList();

        return response;
    }

    //튜토리얼 완료 등록 함수
    public MemberTutorialSaveResponse completeTutorial(Long memberId) {
        Member findMember = getMember(memberId);
        findMember.completeTutorial();
        return MemberTutorialSaveResponse.from(findMember);

    }

    //잔여포인트 조회하기
    public MemberFindRemainPointsResponse findRemainPoints(Long memberId) {
        Member findMember = getMember(memberId);
        return MemberFindRemainPointsResponse.from(findMember.getRemainPoints());
    }

    //랭킹 조회하기
    public MemberRankingAllResponse getRankingList(String range, int pageSize, Long lastId, Integer lastPoint) {

        List<Member> memberList = new ArrayList<>();

        if(range.equals("all")){
           memberList = memberRepository.findByTotalPointRanking(pageSize, lastId, lastPoint);
        }
        else if(range.equals("month")){
            memberList = memberRepository.findByMonthPointRanking(pageSize, lastId, lastPoint);
        }

        //그 다음 페이지 존재 여부 확인
        boolean nextPage = memberList.size() > pageSize;
        if(nextPage) { memberList.remove(memberList.size() - 1);
        }

        //반환
        return MemberRankingAllResponse.builder()
                .nextPage(nextPage)
                .memberDtoList(memberList.stream()
                        .map(m-> MemberRankingAllResponse.MemberDto.of(m, range))
                        .collect(Collectors.toList()))
                .build();
    }

    // 피드 좋아요 하기
    public FeedLikeResponse saveFeedLike(Long memberId, Long feedId) {
        Member member = getMember(memberId);
        Feed feed = getFeed(feedId);

        // 중복 예외 처리
        if(memberPreferredFeedRepository.existsByMemberAndFeed(member, feed)) {
            throw new CustomException(ErrorCode.EXIST_MEMBER_PREFERRED_FEED);
        }

        MemberPreferredFeed memberPreferredFeed = MemberPreferredFeed.createMemberPreferredFeed(feed, member);

        memberPreferredFeedRepository.save(memberPreferredFeed);

        return FeedLikeResponse.from(memberPreferredFeed.getId());

    }

    //id값으로 멤버 찾기 -> 중복 코드 줄이기
    private Member getMember(long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
    }

    private Feed getFeed(long feedId){
        return feedRepository.findById(feedId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FEED));
    }
}