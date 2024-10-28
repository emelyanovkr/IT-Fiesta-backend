package emv.backend.spring.fiesta.controller;

import emv.backend.spring.fiesta.dto.AppUserDTO;
import emv.backend.spring.fiesta.dto.AuthenticationDTO;
import emv.backend.spring.fiesta.model.userSchema.AppUser;
import emv.backend.spring.fiesta.service.userSchema.AuthenticationService;
import emv.backend.spring.fiesta.service.userSchema.RegistrationService;
import emv.backend.spring.fiesta.util.FailedValidationResponseHandler;
import jakarta.validation.Valid;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final RegistrationService registrationService;
  private final AuthenticationService authenticationService;
  private final ModelMapper modelMapper;

  public AuthController(
      RegistrationService registrationService,
      AuthenticationService authenticationService,
      ModelMapper modelMapper) {
    this.registrationService = registrationService;
    this.authenticationService = authenticationService;
    this.modelMapper = modelMapper;
  }

  // TODO: Consider to do some custom classes as Responses, maybe interface?
  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, String> register(
      @RequestBody @Valid AppUserDTO appUserDTO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return FailedValidationResponseHandler.handleErrorMessaging(bindingResult);
    }
    String jwtToken;
    jwtToken = registrationService.registerUser(modelMapper.map(appUserDTO, AppUser.class));
    return Map.of("token", jwtToken);
  }

  @PostMapping("/login")
  public Map<String, String> login(
      @RequestBody @Valid AuthenticationDTO authenticationDTO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return FailedValidationResponseHandler.handleErrorMessaging(bindingResult);
    }
    String jwtToken;

    AppUser user = modelMapper.map(authenticationDTO, AppUser.class);
    jwtToken = authenticationService.authenticateUser(user);
    return Map.of("token", jwtToken);
  }
}
