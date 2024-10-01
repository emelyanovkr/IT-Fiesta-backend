package emv.backend.spring.fiesta.controller;

import emv.backend.spring.fiesta.dto.AppUserDTO;
import emv.backend.spring.fiesta.dto.AuthenticationDTO;
import emv.backend.spring.fiesta.service.AuthenticationService;
import emv.backend.spring.fiesta.service.RegistrationService;
import emv.backend.spring.fiesta.util.FailedValidationResponseHandler;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

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
      return FailedValidationResponseHandler.handleErrorMessaging(bindingResult);
    }
    String jwtToken;
    jwtToken = registrationService.registerUser(appUserDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("token", jwtToken));
  }

  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> login(
      @RequestBody @Valid AuthenticationDTO authenticationDTO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return FailedValidationResponseHandler.handleErrorMessaging(bindingResult);
    }
    String jwtToken;
    jwtToken = authenticationService.authenticateUser(authenticationDTO);
    return ResponseEntity.status(HttpStatus.OK).body(Map.of("token", jwtToken));
  }
}
