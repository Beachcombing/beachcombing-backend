package beachcombing.backend.domain.member.dto;

import beachcombing.backend.domain.member.domain.Member;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class MemberDto {

    private Long id;
    private String name;
    private String phone;
    private String email;

    public static MemberDto from(Member member) {

        return MemberDto.builder()
                .id(member.getId())
                .name(member.getProfile().getName())
                .phone(member.getProfile().getPhone())
                .email(member.getProfile().getEmail())
                .build();
    }
}
