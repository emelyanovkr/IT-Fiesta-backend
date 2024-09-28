package emv.backend.spring.fiesta.service;

import emv.backend.spring.fiesta.dto.AppUserDTO;
import emv.backend.spring.fiesta.model.AppUser;
import emv.backend.spring.fiesta.model.Role;
import emv.backend.spring.fiesta.repository.AppUserRepository;
import emv.backend.spring.fiesta.security.jwtutil.JwtTokenHandling;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

  private final AppUserRepository appUserRepository;
  private final PasswordEncoder passwordEncoder;

  private final JwtTokenHandling jwtTokenHandling;

  private final ModelMapper modelMapper;

  private final String EMAIL_ALREADY_EXIST_ERROR_MSG =
      "Email already exist, please, select another email.";
  private final String USERNAME_ALREADY_EXIST_ERROR_MSG =
      "Username already exist, please, select another username.";

  public RegistrationService(
      AppUserRepository appUserRepository,
      PasswordEncoder passwordEncoder,
      JwtTokenHandling jwtTokenHandling,
      ModelMapper modelMapper) {
    this.appUserRepository = appUserRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtTokenHandling = jwtTokenHandling;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public String registerUser(AppUserDTO appUserDTO) {
    AppUser appUser = modelMapper.map(appUserDTO, AppUser.class);
    appUser.setRole(Role.USER);

    // TODO: Provide custom exceptions
    if (appUserRepository.existsByEmail(appUser.getEmail())) {
      throw new RuntimeException(EMAIL_ALREADY_EXIST_ERROR_MSG);
    }

    if (appUserRepository.existsByUsername(appUser.getUsername())) {
      throw new RuntimeException(USERNAME_ALREADY_EXIST_ERROR_MSG);
    }

    appUser.setPassword(passwordEncoder.encode(appUserDTO.getPassword()));
    appUserRepository.save(appUser);

    return jwtTokenHandling.generateToken(appUser.getUsername());
  }
}
