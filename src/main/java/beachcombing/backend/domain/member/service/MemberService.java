package beachcombing.backend.domain.member.service;


import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.dto.UpdateMemberInfoRequest;
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
    public MemberFindOneResponse findMember(long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        MemberFindOneResponse response = memberMapper.toUserFindOneResponse(member);

        return response;
    }

    //회원 정보 수정
    public void updateInfo(long id, UpdateMemberInfoRequest request) {

        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        validateDuplicatedNickname(request.getNickname());
        findMember.getProfile().updateNicknameAndImage(request);

    }

    //중복 닉네임 검증

    @Transactional(readOnly = true)
    private void validateDuplicatedNickname(String nickname) {

        Boolean NicknameCheck = memberRepository.existsByProfileNickname(nickname);
        if(NicknameCheck) {
            throw new CustomException(ErrorCode.EXIST_USER_NICKNAME);
        }
    }


}