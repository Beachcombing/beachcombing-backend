package beachcombing.backend.domain.trashcan.service;

import beachcombing.backend.domain.image.service.ImageService;
import beachcombing.backend.domain.trashcan.domain.repository.TrashcanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TrashcanService {

    private final TrashcanRepository trashcanRepository;
    private final ImageService imageService; // TODO: 서비스 의존관계 끊어내기 (helper로 분리)


}
