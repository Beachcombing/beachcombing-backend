package beachcombing.backend.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NicknameCheckRequest {
    private String nickname;
}
