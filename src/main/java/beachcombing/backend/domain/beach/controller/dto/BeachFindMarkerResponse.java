package beachcombing.backend.domain.beach.controller.dto;

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
}
