package beachcombing.backend.domain.member.dto;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class MemberFindResponse {

    private Long id;
    private String email;
    private String nickName;
    private String image;
    private Integer totalPoint;
    private Integer monthPoint;
    private Integer purchasePoint;
    private Boolean profilePublic;

}