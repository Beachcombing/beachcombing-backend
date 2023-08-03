package beachcombing.backend.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberFindOneResponse {
    private MemberDto member;
}

