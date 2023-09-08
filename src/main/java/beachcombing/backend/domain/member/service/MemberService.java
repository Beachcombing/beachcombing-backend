package beachcombing.backend.domain.member.service;


import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.dto.MemberFindResponse;
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
    @Transactional(readOnly = true)
    public MemberFindResponse findMember(long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        MemberFindResponse response = memberMapper.toMemberFindResponse(member);

        return response;
    }

}