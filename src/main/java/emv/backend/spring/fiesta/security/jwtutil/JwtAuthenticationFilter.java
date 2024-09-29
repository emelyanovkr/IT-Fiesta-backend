package emv.backend.spring.fiesta.security.jwtutil;

import com.auth0.jwt.exceptions.JWTVerificationException;
import emv.backend.spring.fiesta.service.AppUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

  private final JwtTokenHandling jwtTokenHandling;
  private final AppUserDetailsService appUserDetailsService;

  private static final String BLANK_JWT_TOKEN_ERROR_MSG = "JWT TOKEN IS BLANK, PLEASE RETRY";
  private static final String USERNAME_NOT_FOUND_ERROR_MSG = "USER WITH THIS USERNAME IS NOT FOUND";
  private static final String JWT_VERIFICATION_ERROR_MSG = "JWT VERIFICATION FAILED";

  public JwtAuthenticationFilter(
      JwtTokenHandling jwtTokenHandling, AppUserDetailsService appUserDetailsService) {
    this.jwtTokenHandling = jwtTokenHandling;
    this.appUserDetailsService = appUserDetailsService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      String jwtToken = authHeader.substring(7);

      if (jwtToken.isBlank()) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, BLANK_JWT_TOKEN_ERROR_MSG);
        return;
      } else {
        try {
          String username = jwtTokenHandling.validateToken(jwtToken);
          UserDetails userDetails = appUserDetailsService.loadUserByUsername(username);

          UsernamePasswordAuthenticationToken authenticationToken =
              new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities());

          if (SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          }
        } catch (UsernameNotFoundException e) {
          LOGGER.error(USERNAME_NOT_FOUND_ERROR_MSG, e);
          response.sendError(HttpServletResponse.SC_UNAUTHORIZED, USERNAME_NOT_FOUND_ERROR_MSG);
          return;
        } catch (JWTVerificationException e) {
          LOGGER.error(JWT_VERIFICATION_ERROR_MSG, e);
          response.sendError(HttpServletResponse.SC_BAD_REQUEST, JWT_VERIFICATION_ERROR_MSG);
          return;
        } catch (Exception e) {
          LOGGER.error("FILTER EXCEPTION OCCURRED - ", e);
          response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
          return;
        }
      }
    }
    filterChain.doFilter(request, response);
  }
}
