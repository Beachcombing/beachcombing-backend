package beachcombing.backend.domain.member.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberUpdateRequest {

    private String nickname;
    //private String image;
    private Boolean isChanged;
}
