package beachcombing.backend.domain.member.controller.dto;

import beachcombing.backend.domain.beach.controller.dto.BeachFindResponse;
import beachcombing.backend.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRankingAllResponse {

    private Boolean nextPage;
    private List<MemberDto> memberDtoList;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberDto {
        public Long id;
        public String nickname;
        public String image;
        public Integer point;

        //TODO : image 가 어떤 이미지를 말하는 건지 (프로필 사진 OR 또다른 새로운 이미지인지)
        public static MemberDto of(Member member, String range) {
            return MemberDto.builder()
                    .id(member.getId())
                    .nickname(member.getProfile().getNickname())
                    .image(member.getProfile().getImage())
                    .point(range.equals("all") ? member.getTotalPoint() : member.getMonthPoint() )
                    .build();
        }
    }


}
