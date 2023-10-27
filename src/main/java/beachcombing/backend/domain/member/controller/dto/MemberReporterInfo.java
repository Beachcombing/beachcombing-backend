package beachcombing.backend.domain.member.controller.dto;

import beachcombing.backend.domain.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberReporterInfo {

    private String nickname;
    private String image;

    public static MemberReporterInfo from(Member member) {

        return MemberReporterInfo.builder()
                .nickname(member.getNickname())
                .image(member.getImage())
                .build();
    }
}
