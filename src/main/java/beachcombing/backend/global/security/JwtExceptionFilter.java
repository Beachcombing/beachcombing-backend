package beachcombing.backend.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request, response); // JwtAuthorizationFilter로 이동
        } catch (JwtException ex) {
            setErrorResponse(response, ex); // JwtAuthorizationFilter에서 예외 발생하면 호출됨
        }
    }

    public void setErrorResponse(HttpServletResponse response, Throwable ex) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        Map<String, Object> body = new HashMap<>();
        if (ex.getMessage().equals("TOKEN_EXPIRED")) {
            body.put("error", "토큰이 만료되었습니다.");
        } else {
            body.put("error", "토큰이 유효하지 않습니다.");
        }
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}