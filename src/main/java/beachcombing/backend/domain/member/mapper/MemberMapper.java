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

    public AuthInfo toAuthInfo(String loginId, String password, String provider) {

        return AuthInfo.builder()
                .loginId(loginId)
                .password(bCryptPasswordEncoder.encode(password))
                .provider(provider)
                .build();
    }

    public Profile toProfile(String email, String nickName, String image) {

        return Profile.builder()
                .email(email)
                .nickname(nickName)
                .image(image)
                .build();
    }

    public Member toEntity(AuthJoinRequest request) {

        AuthInfo authInfo = toAuthInfo(
                request.getLoginId(),
                request.getPassword(),
                request.getProvider());

        Profile profile = toProfile(
                request.getEmail(),
                request.getNickName(),
                request.getImage());

        return Member.createUser(profile, authInfo);
    }
}
