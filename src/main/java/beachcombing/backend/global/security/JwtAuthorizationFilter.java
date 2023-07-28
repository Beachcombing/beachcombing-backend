package beachcombing.backend.global.security;

import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.repository.MemberRepository;
import beachcombing.backend.global.security.auth.PrincipalDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository, JwtTokenProvider jwtTokenProvider) {

        super(authenticationManager);
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(JwtProperties.HEADER_STRING);

        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, ""); // Bearer 제거

        String username = jwtTokenProvider.getUsernameFromAccessToken(token); // 토큰 검증 -> 인증 완료 (AuthenticationManager 대체)

        if (username != null) { // 권한 구분 위해 Authentication 객체를 생성해 세션에 저장

            Member member = memberRepository.findByAuthInfoLoginId(username);
            PrincipalDetails principalDetails = new PrincipalDetails(member);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null);
            SecurityContextHolder.getContext().setAuthentication(authentication); // 이때 자동으로 UserDetailsService의 loadByUsername 호출됨
        }

        chain.doFilter(request, response); // 다음 필터로 진행
    }
}
