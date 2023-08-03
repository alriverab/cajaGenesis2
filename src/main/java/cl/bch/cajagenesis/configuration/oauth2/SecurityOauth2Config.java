package cl.bch.cajagenesis.configuration.oauth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;


/**
 * Configuracion de verificación de autenticación por JWT
 *
 * @author Felipe Montoya - NTT Data
 */
@Configuration
@EnableWebSecurity
public class SecurityOauth2Config extends WebSecurityConfigurerAdapter {

    @Value("${bch.cloud.security.oauth2.authorities-claim-name:roles}")
    private String authoritiesClaimName;

    @Value("${bch.cloud.security.oauth2.authority-prefix:ROLE_}")
    private String authorityPrefix;

    /**
     * Paths que pueden ser invocados sin token
     */
    @Value("${bch.cloud.security.public.paths:[]}")
    private String[] publicPaths;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().and()
                .authorizeRequests()
                .antMatchers(
                        "/login",
                        "/"
                ).permitAll()
                .antMatchers("/v1.0/service/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/cajagenesis/**")
                .antMatchers("/index")
                .antMatchers("/health", "/health/**")
                .antMatchers("/info", "/info/**")
                .antMatchers("/restart", "/restart/**")
                .antMatchers("/prometheus", "/prometheus/**")
                .antMatchers("/v*/api-docs/**", "/swagger-ui.html", "/swagger-ui/**");
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new CustomJwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter());
        return jwtAuthenticationConverter;
    }

    private JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(authoritiesClaimName);
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(authorityPrefix);
        return jwtGrantedAuthoritiesConverter;
    }
}
