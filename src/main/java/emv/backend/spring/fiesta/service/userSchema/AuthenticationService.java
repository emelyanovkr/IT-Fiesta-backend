package emv.backend.spring.fiesta.service.userSchema;

import emv.backend.spring.fiesta.model.userSchema.AppUser;
import emv.backend.spring.fiesta.security.jwtutil.JwtTokenHandling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenHandling jwtTokenHandling;

  public AuthenticationService(
      AuthenticationManager authenticationManager, JwtTokenHandling jwtTokenHandling) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenHandling = jwtTokenHandling;
  }

  public String authenticateUser(AppUser authentication) throws BadCredentialsException {
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
            authentication.getUsername(), authentication.getPassword());

    authenticationManager.authenticate(authenticationToken);

    return jwtTokenHandling.generateToken(authentication.getUsername());
  }
}
