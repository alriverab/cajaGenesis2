package cl.bch.cajagenesis.configuration.oauth2;

import cl.bch.cajagenesis.configuration.oauth2.util.JwtHolder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collection;

@NoArgsConstructor
public class CustomJwtAuthenticationConverter extends JwtAuthenticationConverter {

    @Override
    protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        JwtHolder.setCurrentJwt(jwt);
        return super.extractAuthorities(jwt);
    }
    
}
