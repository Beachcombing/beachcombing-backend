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
        // Return an empty list since there are no authorities for this member
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
