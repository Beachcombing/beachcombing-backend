package beachcombing.backend.domain.auth.controller;

import beachcombing.backend.domain.auth.controller.dto.*;
import beachcombing.backend.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    // 일반 회원가입 (백엔드 테스트용)
    @PostMapping("join")
    public ResponseEntity<Void> join(@RequestBody AuthJoinRequest authJoinRequest) {

        authService.join(authJoinRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 일반 로그인 ( 백엔드 테스트용 )
    @PostMapping("login")
    public ResponseEntity<AuthLoginResponse> login(@RequestBody AuthLoginRequest request) {

        AuthLoginResponse response =authService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    // 구글로그인
    @PostMapping("google")
    public ResponseEntity<AuthLoginResponse> googleLogin(@RequestBody AuthGoogleLoginRequest request){
        AuthLoginResponse response = authService.googleLogin(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // AccessToken 재발급
    @PostMapping("token")
    public ResponseEntity<AuthRefreshResponse> refresh(@RequestBody Map<String, String> refreshToken){
        AuthRefreshResponse response = authService.refresh(refreshToken.get("refreshToken"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 로그아웃
    @PostMapping("logout")
    public ResponseEntity<Void> logout(@RequestBody Map<String,String> accessToken){
        authService.logout(accessToken.get("accessToken"));
        return ResponseEntity.status(HttpStatus.OK).build();
    }





}


