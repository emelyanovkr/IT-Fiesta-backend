package emv.backend.spring.fiesta.model.userAccount;

import jakarta.persistence.*;

@Entity
@Table(schema = "user_account_schema", name = "user_roles")
public class UserRole {

  @EmbeddedId private UserRoleKey id;

  @ManyToOne
  @MapsId("userId")
  @JoinColumn(name = "user_id")
  private AppUser appUser;

  @ManyToOne
  @MapsId("roleId")
  @JoinColumn(name = "role_id")
  private Role role;

  public UserRole() {}

  public UserRoleKey getId() {
    return id;
  }

  public void setId(UserRoleKey id) {
    this.id = id;
  }

  public AppUser getAppUser() {
    return appUser;
  }

  public void setAppUser(AppUser appUser) {
    this.appUser = appUser;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
