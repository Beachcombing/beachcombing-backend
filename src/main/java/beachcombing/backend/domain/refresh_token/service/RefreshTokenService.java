package beachcombing.backend.domain.refresh_token.service;

import beachcombing.backend.domain.refresh_token.domain.RefreshToken;
import beachcombing.backend.domain.refresh_token.domain.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(String refreshToken, String keyLoginId) {

        RefreshToken newRefreshToken = RefreshToken.createRefreshToken(refreshToken, keyLoginId);

        // 기존 토큰이 있으면 업데이트하고, 없으면 새로 생성하여 저장
        refreshTokenRepository.findByKeyLoginId(keyLoginId)
                .ifPresentOrElse(
                        (findRefreshToken) -> findRefreshToken.updateToken(refreshToken),
                        () -> refreshTokenRepository.save(newRefreshToken)
                );
    }
}