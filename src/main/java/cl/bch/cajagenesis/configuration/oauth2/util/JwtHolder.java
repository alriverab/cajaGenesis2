package cl.bch.cajagenesis.configuration.oauth2.util;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.Assert;


public final class JwtHolder {

    private static final InheritableThreadLocal<Jwt> JWT_TL = new InheritableThreadLocal<>();
    
    public static void setCurrentJwt(Jwt jwt) {
        JWT_TL.set(jwt);
    }
    
    public static Jwt getCurrentJwt() {
        Jwt jwt = JWT_TL.get();
        Assert.notNull(jwt, "Current JWT cannot be null");
        return jwt;
    }
}
