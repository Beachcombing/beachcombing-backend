package beachcombing.backend.domain.member.service;


import beachcombing.backend.domain.member.controller.dto.MemberRankingAllResponse;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.controller.dto.MemberFindResponse;
import beachcombing.backend.domain.member.controller.dto.MemberUpdateRequest;
import beachcombing.backend.domain.member.mapper.MemberMapper;
import beachcombing.backend.domain.member.domain.repository.MemberRepository;
import beachcombing.backend.global.config.exception.CustomException;
import beachcombing.backend.global.config.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    //회원 정보 조회
    @Transactional(readOnly = true)
    public MemberFindResponse findMember(long memberId) {

        Member findMember = findMemberById(memberId);
        MemberFindResponse response = memberMapper.toMemberFindResponse(findMember);

        return response;
    }

    //회원 정보 수정
    public void updateMember(long memberId, MemberUpdateRequest request, Boolean isChanged) {

        Member findMember = findMemberById(memberId);
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
    public void updateProfilePublic(Long memberId, Boolean profilePublic) {

        Member findMember = findMemberById(memberId);
        findMember.updateProfilePublic(profilePublic);
    }

    //탈퇴하기
    public void deleteMember(Long memberId) {
        Member findMember = findMemberById(memberId);
        memberRepository.delete(findMember);
    }

    //랭킹 조회하기
    public MemberRankingAllResponse getRankingList(String range, int pageSize, long lastId, int lastPoint) {
        List<Member> memberList = new ArrayList<>();

        if(range.equals("all")){
           memberList = memberRepository.findByMonthPointRanking(pageSize, lastId, lastPoint);
        }
        else if(range.equals("month")){
            memberList = memberRepository.findByMonthPointRanking(pageSize, lastId, lastPoint);
        }
        if(memberList.isEmpty()){
            throw new CustomException(ErrorCode.BAD_REQUEST_PARAM);
        }

        //그 다음 페이지 존재 여부 확인
        boolean nextPage = memberList.size() > pageSize;
        if(nextPage) { memberList.remove(memberList.size() - 1); }

        //반환
        return MemberRankingAllResponse.builder()
                .nextPage(nextPage)
                .memberDtoList(memberList.stream()
                        .map(m-> MemberRankingAllResponse.MemberDto.of(m, range))
                        .collect(Collectors.toList()))
                .build();

    }




    //id값으로 멤버 찾기 -> 중복 코드 줄이기
    private Member findMemberById(long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
    }

}