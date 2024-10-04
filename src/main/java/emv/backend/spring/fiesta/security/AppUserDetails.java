package emv.backend.spring.fiesta.security;

import emv.backend.spring.fiesta.model.userAccount.AppUser;
import emv.backend.spring.fiesta.model.userAccount.UserRole;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUserDetails implements UserDetails {

  private final AppUser appUser;

  public AppUserDetails(AppUser appUser) {
    this.appUser = appUser;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<UserRole> roles = appUser.getRoles();
    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

    for (UserRole role : roles) {
      authorityList.add(new SimpleGrantedAuthority(role.getRole().getName().getRepresentation()));
    }

    return authorityList;
  }

  @Override
  public String getPassword() {
    return appUser.getPassword();
  }

  @Override
  public String getUsername() {
    return appUser.getUsername();
  }
}
