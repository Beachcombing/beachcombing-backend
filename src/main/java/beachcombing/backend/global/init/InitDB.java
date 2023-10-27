package beachcombing.backend.global.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

//    @PostConstruct
//    public void init() {
//        initService.initDatabase();
//        initService.saveTrashcanFromApi();
//    }
}