package beachcombing.backend.domain.beach.controller.dto;

import beachcombing.backend.domain.beach.domain.Beach;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeachFindMarkerResponse {
    public Long id;
    public String name;
    public String lat;
    public String lng;
    public String memberImage; // 최근 청소기록 유저 프로필이미지

    public static BeachFindMarkerResponse of(Beach beach, String memberImage){
        return BeachFindMarkerResponse.builder()
                .id(beach.getId())
                .lat(beach.getLocation().getLat().toString())
                .lng(beach.getLocation().getLng().toString())
                .memberImage(memberImage)
                .build();
    }
}
