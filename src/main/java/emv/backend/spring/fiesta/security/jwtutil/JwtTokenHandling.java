package emv.backend.spring.fiesta.security.jwtutil;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenHandling {
  @Value("${jwt.secret}")
  private String SECRET;

  @Value("${jwt.expiration_hours}")
  private long EXPIRATION_HOURS;

  @Value("${jwt.token_issuer}")
  private String TOKEN_ISSUER;

  private Algorithm USED_ALGORITHM;
  private String SUBJECT_USERNAME;

  @PostConstruct
  public void init() {
    this.USED_ALGORITHM = Algorithm.HMAC256(SECRET);
  }

  public String generateToken(String username) {
    SUBJECT_USERNAME = username;
    return JWT.create()
        .withSubject(username)
        .withClaim("username", username)
        .withIssuedAt(new Date())
        .withIssuer(TOKEN_ISSUER)
        .withExpiresAt(Instant.now().plus(Duration.ofHours(EXPIRATION_HOURS)))
        .sign(USED_ALGORITHM);
  }

  public String validateToken(String token) throws JWTVerificationException {
    JWTVerifier verifier =
        JWT.require(USED_ALGORITHM).withSubject(SUBJECT_USERNAME).withIssuer(TOKEN_ISSUER).build();
    DecodedJWT decodedJWT = verifier.verify(token);
    return decodedJWT.getClaim("username").asString();
  }
}
