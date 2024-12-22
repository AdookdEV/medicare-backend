package kz.medicare.utils;

import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class JwtUtilTest {

    @Autowired
    JwtUtil jwtUtil;

    private final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImlhdCI6MTczNDM1MzgxNiwiZXhwIjoxNzM0MzUzODIxfQ.12ijkstN0oxK9ZUwo-1iMd7b1Z11_M9KxF1Z5M3Z3XA";

    @Test
    void throwJwtException_whenExpiredToken() {
        assertThrows(JwtException.class, () -> {
            var username = jwtUtil.extractUsername(token);
        });
    }
}