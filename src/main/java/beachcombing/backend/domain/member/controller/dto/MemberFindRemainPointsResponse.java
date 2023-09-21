package beachcombing.backend.domain.member.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberFindRemainPointsResponse {

    private Integer remainPoints;

    public static MemberFindRemainPointsResponse from(Integer remainPoints) {
        return MemberFindRemainPointsResponse.builder()
                .remainPoints(remainPoints)
                .build();
    }
}
