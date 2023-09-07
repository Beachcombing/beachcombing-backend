package beachcombing.backend.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberUpdateOneRequest {

    private String nickname;
    //private String image;
    private Boolean isChanged;
}
