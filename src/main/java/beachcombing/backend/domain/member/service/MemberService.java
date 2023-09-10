package beachcombing.backend.domain.member.service;


import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.dto.MemberUpdateRequest;
import beachcombing.backend.domain.member.dto.MemberFindOneResponse;
import beachcombing.backend.domain.member.mapper.MemberMapper;
import beachcombing.backend.domain.member.repository.MemberRepository;
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
    public MemberFindOneResponse findMember(long userId) {

        Member findMember = findMemberById(userId);
        MemberFindOneResponse response = memberMapper.toUserFindOneResponse(findMember);

        return response;
    }

    //회원 정보 수정
    public void updateMember(long userId, MemberUpdateRequest request, Boolean isChanged) {

        Member findMember = findMemberById(userId);
        checkNickname(request.getNickname());
        findMember.getProfile().updateNicknameAndImage(request, isChanged);

    }

    //중복 닉네임 검증\
    @Transactional(readOnly = true)
    public void checkNickname(String nickname) {

        Boolean nicknameCheck = memberRepository.existsByProfileNickname(nickname);
        if(nicknameCheck) {
            throw new CustomException(ErrorCode.EXIST_USER_NICKNAME);
        }
    }

    //id값으로 멤버 찾기 -> 중복 코드 줄이기
    @Transactional(readOnly = true)
    public Member findMemberById(long userId){
        return memberRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
    }

    //프로필 공개여부 변경하기
    public void updateProfilePublic(Long userId, Boolean profilePublic) {

        Member findMember = findMemberById(userId);
        findMember.updateProfilePublic(profilePublic);
    }
}