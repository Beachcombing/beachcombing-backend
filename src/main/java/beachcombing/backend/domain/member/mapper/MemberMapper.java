package beachcombing.backend.domain.member.mapper;

import beachcombing.backend.domain.auth.controller.dto.AuthJoinRequest;
import beachcombing.backend.domain.member.domain.AuthInfo;
import beachcombing.backend.domain.member.domain.Profile;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.dto.MemberFindResponse;
import lombok.RequiredArgsConstructor;;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberMapper {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public static MemberFindResponse toMemberFindResponse(Member member) {

        return MemberFindResponse.builder()
                .id(member.getId())
                .email(member.getProfile().getEmail())
                .nickName(member.getProfile().getNickname())
                .image(member.getProfile().getImage())
                .totalPoint(member.getTotalPoint())
                .monthPoint(member.getMonthPoint())
                .purchasePoint(member.getPurchasePoint())
                .profilePublic(member.getProfilePublic())
                .build();
    }

    public AuthInfo toAuthInfo(String loginId, String password, String provider) {

        return AuthInfo.builder()
                .loginId(loginId)
                .password(bCryptPasswordEncoder.encode(password))
                .provider(provider)
                .build();
    }

    public Profile toProfile(String email, String nickName, String image, String role) {

        return Profile.builder()
                .email(email)
                .nickname(nickName)
                .image(image)
                .role(role)
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
                request.getImage(),
                request.getRole());

        return Member.createMember(profile, authInfo);
    }
}
