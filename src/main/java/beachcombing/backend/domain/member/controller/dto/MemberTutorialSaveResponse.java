package beachcombing.backend.domain.member.controller.dto;

import beachcombing.backend.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberTutorialSaveResponse {

    private Long id;

    public static MemberTutorialSaveResponse from(Member member)
    {
        return MemberTutorialSaveResponse.builder()
                .id(member.getId())
                .build();
    }

}
