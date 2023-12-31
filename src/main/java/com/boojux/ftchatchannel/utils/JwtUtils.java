package com.boojux.ftchatchannel.utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String JWT_SECRET_KEY ; // 替换为你自己的JWT Secret
    public String getValueFromJwt(String token, String key) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims(); //Key is the Claim name
            Claim claim = claims.get(key);
            return claim == null ? null : claim.asString();
        } catch (JWTDecodeException exception) {
            // Invalid token
            return null;
        }
    }
}

