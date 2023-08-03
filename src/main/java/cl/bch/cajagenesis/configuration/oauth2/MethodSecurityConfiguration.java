package cl.bch.cajagenesis.configuration.oauth2;

import lombok.NoArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * Configuracion para permitir proteger endpoint con roles
 * 
 * @author Felipe Montoya - NTT Data
 *
 */
@NoArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, 
    securedEnabled = true, 
    jsr250Enabled = true) //Para poder usar @RolesAllowed
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

}
