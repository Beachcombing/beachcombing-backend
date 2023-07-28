package beachcombing.backend.domain.auth.controller;

import beachcombing.backend.domain.auth.dto.AuthJoinRequest;
import beachcombing.backend.domain.auth.dto.AuthLoginRequest;
import beachcombing.backend.domain.auth.dto.AuthLoginResponse;
import beachcombing.backend.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    // 일반 회원가입 (백엔드 테스트용)
    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody AuthJoinRequest authJoinRequest) {

        authService.join(authJoinRequest);
        return ResponseEntity.ok().build();
    }

    // 일반 로그인 ( 백엔드 테스트용 )
    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponse> login(@RequestBody AuthLoginRequest request) {

        AuthLoginResponse response =authService.login(request);
        return ResponseEntity.ok().body(response);
    }
}


