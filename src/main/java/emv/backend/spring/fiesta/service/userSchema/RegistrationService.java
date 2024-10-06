package emv.backend.spring.fiesta.service.userSchema;

import emv.backend.spring.fiesta.dto.AppUserDTO;
import emv.backend.spring.fiesta.exception.EntityAlreadyExistException;
import emv.backend.spring.fiesta.model.userSchema.AppUser;
import emv.backend.spring.fiesta.model.userSchema.RoleType;
import emv.backend.spring.fiesta.model.userSchema.UserRole;
import emv.backend.spring.fiesta.model.userSchema.UserRoleKey;
import emv.backend.spring.fiesta.repository.userSchema.AppUserRepository;
import emv.backend.spring.fiesta.repository.userSchema.RoleRepository;
import emv.backend.spring.fiesta.repository.userSchema.UserRoleRepository;
import emv.backend.spring.fiesta.security.jwtutil.JwtTokenHandling;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

  private final AppUserRepository appUserRepository;
  private final RoleRepository roleRepository;
  private final UserRoleRepository userRoleRepository;
  private final PasswordEncoder passwordEncoder;

  private final JwtTokenHandling jwtTokenHandling;

  private final ModelMapper modelMapper;

  private static final String EMAIL_ALREADY_EXIST_ERROR_MSG =
      "Email already exist, please, select another email.";
  private static final String USERNAME_ALREADY_EXIST_ERROR_MSG =
      "Username already exist, please, select another username.";

  public RegistrationService(
      AppUserRepository appUserRepository,
      RoleRepository roleRepository,
      UserRoleRepository userRoleRepository,
      PasswordEncoder passwordEncoder,
      JwtTokenHandling jwtTokenHandling,
      ModelMapper modelMapper) {
    this.appUserRepository = appUserRepository;
    this.roleRepository = roleRepository;
    this.userRoleRepository = userRoleRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtTokenHandling = jwtTokenHandling;
    this.modelMapper = modelMapper;
  }

  public UserRoleKey registerUserRoleKey(UserRole userRole) {
    UserRoleKey userRoleKey = new UserRoleKey();
    userRoleKey.setUserId(userRole.getAppUser().getId());
    userRoleKey.setRoleId(userRole.getRole().getId());
    return userRoleKey;
  }

  public void registerRole(AppUser appuser) {
    UserRole userRole = new UserRole();
    userRole.setAppUser(appuser);
    userRole.setRole(
        roleRepository
            .findByName(RoleType.USER)
            .orElseThrow(() -> new EntityNotFoundException(appuser.getUsername())));

    UserRoleKey userRoleKey = registerUserRoleKey(userRole);
    userRole.setId(userRoleKey);
    userRoleRepository.save(userRole);
  }

  @Transactional
  public String registerUser(AppUserDTO appUserDTO) {
    AppUser appUser = modelMapper.map(appUserDTO, AppUser.class);

    if (appUserRepository.existsByEmail(appUser.getEmail())) {
      throw new EntityAlreadyExistException(EMAIL_ALREADY_EXIST_ERROR_MSG);
    }

    if (appUserRepository.existsByUsername(appUser.getUsername())) {
      throw new EntityAlreadyExistException(USERNAME_ALREADY_EXIST_ERROR_MSG);
    }

    appUser.setPassword(passwordEncoder.encode(appUserDTO.getPassword()));
    AppUser savedEntity = appUserRepository.save(appUser);
    registerRole(savedEntity);

    return jwtTokenHandling.generateToken(appUser.getUsername());
  }
}
