package beachcombing.backend.domain.member.dto;

import beachcombing.backend.domain.member.domain.Member;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class MemberDto {

    private Long id;
    private String email;
    private String nickName;
    private String image;
    private Integer totalPoint;
    private Integer monthPoint;
    private Integer purchasePoint;
    private Boolean profilePublic;

    public static MemberDto from(Member member) {

        return MemberDto.builder()
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
}