package beachcombing.backend.domain.token_pair.service;

import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.token_pair.domain.TokenPair;
import beachcombing.backend.domain.token_pair.repository.TokenPairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenPairService {

    private final TokenPairRepository tokenPairRepository;

    public void saveTokenPair(String accessToken, String refreshToken, Member member) {

        TokenPair tokenPair = TokenPair.createTokenPair(accessToken, refreshToken, member);

        // 기존 토큰이 있으면 업데이트하고, 없으면 새로 생성하여 저장
        tokenPairRepository.findByMember(member)
                .ifPresentOrElse(
                        (findTokenPair) -> findTokenPair.updateToken(accessToken, refreshToken),
                        () -> tokenPairRepository.save(tokenPair)
                );
    }
}