package beachcombing.backend.domain.trashcan.service;

import beachcombing.backend.domain.image.service.ImageService;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.domain.repository.MemberRepository;
import beachcombing.backend.domain.member.event.MemberEvent;
import beachcombing.backend.domain.member.event.NotificationCode;
import beachcombing.backend.domain.trashcan.controller.dto.TrashcanFindAllResponse;
import beachcombing.backend.domain.trashcan.controller.dto.TrashcanReportRequest;
import beachcombing.backend.domain.trashcan.domain.Trashcan;
import beachcombing.backend.domain.trashcan.domain.repository.TrashcanRepository;
import beachcombing.backend.global.config.exception.CustomException;
import beachcombing.backend.global.config.exception.ErrorCode;
import beachcombing.backend.global.init.GeocodingHelper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class TrashcanService {

    private final TrashcanRepository trashcanRepository;
    private final MemberRepository memberRepository;
    private final ImageService imageService; // TODO: 서비스 의존관계 끊어내기 (helper로 분리)
    private final GeocodingHelper geocodingHelper;
    private final ApplicationEventPublisher eventPublisher;

    public Long reportTrashcan(Long memberId, TrashcanReportRequest request) {

        Member findMember = getMemberOrThrow(memberId);
        checkExistsImage(request.getImage());
        String imageUrl = imageService.uploadImage(request.getImage());
        Trashcan trashcan = Trashcan.createTrashcanByMember(imageUrl, request.toLocation(), findMember);

        trashcanRepository.save(trashcan);
        return trashcan.getId();
    }

    @Transactional(readOnly = true)
    public List<TrashcanFindAllResponse> findAllReportedTrashcan() {

        List<Trashcan> findTrashcanList = trashcanRepository.findByIsCertified(false);

        return findTrashcanList.stream()
                .map(TrashcanFindAllResponse::from)
                .collect(Collectors.toList());
    }

    public void certifyTrashcan(Long trashcanId) {

        Trashcan findTrashcan = getTrashcanOrThrow(trashcanId);
        if (findTrashcan.getIsCertified()) {
            throw new CustomException(ErrorCode.ALREADY_CERTIFIED_TRASHCAN);
        }

        String address = geocodingHelper.getAddressByCoords(findTrashcan.getLat(), findTrashcan.getLng());
        findTrashcan.certifyTrashcan(address);

        Member findMember = findTrashcan.getMember();
        findMember.updateMemberPoint(2);
        eventPublisher.publishEvent(new MemberEvent(findMember, NotificationCode.TRASHCAN_CREDENTIAL));
    }

    @Transactional(readOnly = true)
    public List<TrashcanFindAllResponse> findAllCertifiedTrashcan() {

        List<Trashcan> findTrashcanList = trashcanRepository.findByIsCertified(true);
        return findTrashcanList.stream()
                .map(TrashcanFindAllResponse::from)
                .collect(Collectors.toList());
    }

    private Member getMemberOrThrow(Long memberId) {

        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
    }

    private Trashcan getTrashcanOrThrow(Long trashcanId) {

        return trashcanRepository.findById(trashcanId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TRASHCAN));
    }

    private void checkExistsImage(MultipartFile image) {

        if (image.isEmpty()) {
            throw new CustomException(ErrorCode.SHOULD_EXIST_IMAGE);
        }
    }
}
