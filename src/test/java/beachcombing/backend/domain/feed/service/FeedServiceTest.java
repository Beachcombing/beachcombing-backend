package beachcombing.backend.domain.feed.service;

import beachcombing.backend.domain.feed.controller.dto.FeedFindAllResponse;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.domain.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FeedServiceTest {

    @Autowired
    FeedService feedService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void findAllFeed() {
        // given
        Member member = memberRepository.findById(6L).orElseThrow();

        // when
        List<FeedFindAllResponse> responses = feedService.findAllFeed(member);

        // then
        Assertions.assertThat(responses.size()).isEqualTo(1);
    }
}