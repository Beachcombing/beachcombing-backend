package beachcombing.backend.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;

@NoArgsConstructor
@Getter
public class UpdateMemberInfoRequest {

    private String nickname;
    //private String image;
    private Boolean isChanged;
}
