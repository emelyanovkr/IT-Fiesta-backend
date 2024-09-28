package emv.backend.spring.fiesta.service;

import emv.backend.spring.fiesta.model.AppUser;
import emv.backend.spring.fiesta.repository.AppUserRepository;
import emv.backend.spring.fiesta.security.AppUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {
  private final AppUserRepository appUserRepository;

  public AppUserDetailsService(AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<AppUser> loadedUser = appUserRepository.findByUsername(username);

    return new AppUserDetails(
        loadedUser.orElseThrow(() -> new UsernameNotFoundException(username)));
  }
}
