package beachcombing.backend.domain.feed.service;

import beachcombing.backend.domain.feed.controller.dto.FeedSaveRequest;
import beachcombing.backend.domain.feed.controller.dto.FeedSaveResponse;
import beachcombing.backend.domain.feed.domain.Feed;
import beachcombing.backend.domain.feed.domain.repository.FeedRepository;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.domain.repository.MemberRepository;
import beachcombing.backend.domain.record.domain.Record;
import beachcombing.backend.domain.record.domain.repository.RecordRepository;
import beachcombing.backend.global.config.exception.CustomException;
import beachcombing.backend.global.config.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FeedService {

    private final FeedRepository feedRepository;
    private final MemberRepository memberRepository;
    private final RecordRepository recordRepository;

    //피드 기록하기
    public FeedSaveResponse saveFeed(Long memberId, FeedSaveRequest feedSaveRequest) {

        Member findMember = findMemberById(memberId);
        Record findRecord = findRecordById(feedSaveRequest.getRecordId());

        checkRecordFeed(findRecord);
        Feed feed = Feed.createFeed(feedSaveRequest.getReview(), findRecord);
        findRecord.updateRecordFeed(feed);

        feedRepository.save(feed);
        return FeedSaveResponse.from(feed);
    }

    //피드 삭제하기
    public void deleteFeed(Long memberId, Long feedId) {

        Member findMember = findMemberById(memberId);
        Feed findFeed = findFeedById(feedId);
        Record findRecord = findFeed.getRecord();

        if(checkFeedOwner(findMember, findFeed))
        {
            findRecord.updateRecordFeed(null);
            feedRepository.deleteById(findFeed.getId());
        }

        feedRepository.delete(findFeed);
    }

    //멤버 존재 여부
    private Member findMemberById(long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
    }

    //피드 존재 여부
    private Feed findFeedById(Long feedId) {
        return feedRepository.findById(feedId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_FEED));
    }

    //레코드 존재 여부
    private Record findRecordById(Long recordId) {
        return recordRepository.findById(recordId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_RECORD));
    }

    //이미 작성된 피드가 있는 레코드인지 검사
    private Boolean checkRecordFeed(Record record) {
        if (record.getFeed() != null) {
            throw new CustomException(ErrorCode.EXIST_FEED_RECORD);
        }
        return true;
    }

    //내가 작성한 피드인지 검사
    private Boolean checkFeedOwner(Member member, Feed feed){
        if( feed.getRecord().getMember().getId() != member.getId())
        {throw new CustomException(ErrorCode.PERMISSION_DENIED);}
        return true;
    }
}
