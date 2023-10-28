package beachcombing.backend.global.init;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    // 처음 초기화할 때만 사용
//    @PostConstruct
//    public void init() {
//        initService.initDatabase();
//        initService.saveTrashcanFromApi();
//    }
}