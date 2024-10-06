package emv.backend.spring.fiesta.service.userSchema;

import emv.backend.spring.fiesta.dto.AuthenticationDTO;
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

  public String authenticateUser(AuthenticationDTO authenticationDTO)
      throws BadCredentialsException {
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
            authenticationDTO.getUsername(), authenticationDTO.getPassword());

    authenticationManager.authenticate(authenticationToken);

    return jwtTokenHandling.generateToken(authenticationDTO.getUsername());
  }
}
