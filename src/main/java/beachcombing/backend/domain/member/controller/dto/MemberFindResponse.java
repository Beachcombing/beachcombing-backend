package beachcombing.backend.domain.member.controller.dto;

import beachcombing.backend.domain.beach.controller.dto.BeachFindResponse;
import beachcombing.backend.domain.beach.domain.Beach;
import beachcombing.backend.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberFindResponse {

    private Long id;
    private String email;
    private String nickName;
    private String image;
    private Integer totalPoint;
    private Integer monthPoint;
    private Integer purchasePoint;
    private Boolean profilePublic;
    public static MemberFindResponse from(Member member) {

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

}