package emv.backend.spring.fiesta.controller;

import emv.backend.spring.fiesta.dto.AppUserDTO;
import emv.backend.spring.fiesta.dto.AuthenticationDTO;
import emv.backend.spring.fiesta.service.AuthenticationService;
import emv.backend.spring.fiesta.service.RegistrationService;
import emv.backend.spring.fiesta.util.EntityAlreadyExistException;
import emv.backend.spring.fiesta.util.ErrorMessagesResponseHandler;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

  private final RegistrationService registrationService;
  private final AuthenticationService authenticationService;

  public AuthController(
      RegistrationService registrationService, AuthenticationService authenticationService) {
    this.registrationService = registrationService;
    this.authenticationService = authenticationService;
  }

  // TODO: Consider to do some custom classes as Responses, maybe interface?
  @PostMapping("/register")
  public ResponseEntity<Map<String, String>> register(
      @RequestBody @Valid AppUserDTO appUserDTO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ErrorMessagesResponseHandler.handleErrorMessaging(bindingResult);
    }
    String jwtToken;
    try {
      jwtToken = registrationService.registerUser(appUserDTO);
    } catch (EntityAlreadyExistException e) {
      LOGGER.error(e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("token", jwtToken));
  }

  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> login(
      @RequestBody @Valid AuthenticationDTO authenticationDTO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ErrorMessagesResponseHandler.handleErrorMessaging(bindingResult);
    }
    String jwtToken;
    try {
      jwtToken = authenticationService.authenticateUser(authenticationDTO);
    } catch (BadCredentialsException e) {
      LOGGER.error(e.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
    }
    return ResponseEntity.status(HttpStatus.OK).body(Map.of("token", jwtToken));
  }
}
