package beachcombing.backend.global.init;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InitService {

    private final EntityManager em;

    @Transactional
    public void initDatabase() {

    }
}