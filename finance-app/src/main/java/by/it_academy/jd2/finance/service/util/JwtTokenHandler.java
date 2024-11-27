package by.it_academy.jd2.finance.service.util;

import by.it_academy.jd2.finance.config.property.JwtProperty;
import by.it_academy.jd2.finance.repository.entity.user.User;
import by.it_academy.jd2.finance.service.dto.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenHandler {

    private final JwtProperty property;

    public JwtTokenHandler(JwtProperty property) {
        this.property = property;
    }

    public static String getTokenFromHeader(String header) {
        return header.split(" ")[1].trim();
    }

    public String generateToken(User user) {
        return Jwts.builder()
                   .setSubject(user.getId().toString())
                   .claim("role", String.format("ROLE_%s", user.getRole().name()))
                   .setIssuer(property.getIssuer())
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7))) // 1 week
                   .signWith(SignatureAlgorithm.HS512, property.getSecret())
                   .compact();
    }

    public TokenDto getTokenDto(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(property.getSecret())
                            .parseClaimsJws(token)
                            .getBody();

        return TokenDto.builder()
                       .setUserId(UUID.fromString(claims.getSubject()))
                       .setRole(claims.get("role", String.class))
                       .build();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(property.getSecret()).parseClaimsJws(token);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException
                 | IllegalArgumentException ex) {
            return false;
        }
    }
}
