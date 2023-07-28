package beachcombing.backend.domain.token_pair.service;

import beachcombing.backend.domain.token_pair.domain.TokenPair;
import beachcombing.backend.domain.token_pair.repository.TokenPairRepository;
import beachcombing.backend.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenPairService {

    private final TokenPairRepository tokenPairRepository;

    public void saveTokenPair(String accessToken, String refreshToken, User user) {

        TokenPair tokenPair = TokenPair.createTokenPair(accessToken, refreshToken, user);

        if (tokenPairRepository.existsByUser(user)) {
            tokenPairRepository.deleteByUser(user); // TODO: 삭제 대신 업데이트로 변경
        }

        tokenPairRepository.save(tokenPair);
    }
}