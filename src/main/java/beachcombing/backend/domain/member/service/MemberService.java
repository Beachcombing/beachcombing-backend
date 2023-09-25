package beachcombing.backend.domain.member.service;


import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.controller.dto.MemberFindResponse;
import beachcombing.backend.domain.member.controller.dto.MemberUpdateRequest;
import beachcombing.backend.domain.member.mapper.MemberMapper;
import beachcombing.backend.domain.member.domain.repository.MemberRepository;
import beachcombing.backend.global.config.exception.CustomException;
import beachcombing.backend.global.config.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void deleteMember(Long memberId) {
        Member findMember = findMemberById(memberId);
        memberRepository.delete(findMember);
    }

    //id값으로 멤버 찾기 -> 중복 코드 줄이기
    private Member findMemberById(long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
    }

}