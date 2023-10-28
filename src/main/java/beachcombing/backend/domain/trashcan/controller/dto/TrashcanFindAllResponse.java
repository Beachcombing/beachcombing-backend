package beachcombing.backend.domain.trashcan.controller.dto;

import beachcombing.backend.domain.member.controller.dto.MemberReporterInfo;
import beachcombing.backend.domain.trashcan.domain.Trashcan;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TrashcanFindAllResponse {

    private Long id;
    private String image;
    private Boolean isCertified;
    private BigDecimal lat;
    private BigDecimal lng;
    private String address;
    private String date;
    private MemberReporterInfo member;

    public static TrashcanFindAllResponse from(Trashcan trashcan) {

        return TrashcanFindAllResponse.builder()
                .id(trashcan.getId())
                .image(trashcan.getImage())
                .isCertified(trashcan.getIsCertified())
                .lat(trashcan.getLat())
                .lng(trashcan.getLng())
                .address(trashcan.getAddress())
                .date(trashcan.getCreatedDate().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .member(MemberReporterInfo.from(trashcan.getMember()))
                .build();
    }
}
