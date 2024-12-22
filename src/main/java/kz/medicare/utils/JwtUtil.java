package kz.medicare.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.access-token.expiration-time}")
    private Long ACCESS_TOKEN_EXPIRATION_TIME;

    @Value("${jwt.refresh-token.expiration-time}")
    private Long REFRESH_TOKEN_EXPIRATION_TIME;

    public String generateAccessToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(getSecretKey())
                .compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(getSecretKey())
                .compact();
    }

    public String extractUsername(String token) {

        var parser = Jwts.parser()
                .verifyWith(getSecretKey())
                .build();

        return parser
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
