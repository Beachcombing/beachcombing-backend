package beachcombing.backend.global.security.auth;

import beachcombing.backend.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@AllArgsConstructor
public class PrincipalDetails implements UserDetails {
    private Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자 권한이 없으므로 빈 컬렉션 반환
        return Collections.emptyList();
    }
    @Override
    public String getPassword() {
        return member.getAuthInfo().getPassword();
    }

    @Override
    public String getUsername() {
        return member.getAuthInfo().getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
