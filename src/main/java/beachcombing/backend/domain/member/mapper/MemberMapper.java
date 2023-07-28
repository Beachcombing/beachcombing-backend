package beachcombing.backend.domain.member.mapper;

import beachcombing.backend.domain.auth.dto.AuthJoinRequest;
import beachcombing.backend.domain.member.domain.AuthInfo;
import beachcombing.backend.domain.member.domain.Profile;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.dto.MemberDto;
import beachcombing.backend.domain.member.dto.MemberFindOneResponse;
import lombok.RequiredArgsConstructor;;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberMapper {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberFindOneResponse toUserFindOneResponse(Member member) {

        return MemberFindOneResponse.builder()
                .member(MemberDto.from(member))
                .build();
    }

    public AuthInfo toAuthInfo(String loginId, String password) {

        return AuthInfo.builder()
                .loginId(loginId)
                .password(bCryptPasswordEncoder.encode(password))
                .build();
    }

    public Profile toProfile(String name, String email, String phone) {

        return Profile.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .build();
    }

    public Member toEntity(AuthJoinRequest request) {

        AuthInfo authInfo = toAuthInfo(
                request.getLoginId(),
                request.getPassword());

        Profile profile = toProfile(
                request.getName(),
                request.getEmail(),
                request.getPhone());

        return Member.createUser(profile, authInfo);
    }
}
