package emv.backend.spring.fiesta.model.userSchema;

import emv.backend.spring.fiesta.model.eventSchema.Host;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(schema = "user_account_schema", name = "user_data")
public class AppUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "Username can't be blank")
  @NotNull(message = "Username can't be null")
  @Column(name = "username", unique = true, nullable = false)
  private String username;

  @NotBlank(message = "Password can't be blank")
  @NotNull(message = "Password can't be null")
  @Column(name = "password")
  private String password;

  @Email(message = "Please, check your email meets requirements")
  @NotBlank(message = "Email can't be blank")
  @NotNull(message = "Email can't be null")
  @Column(name = "email")
  private String email;

  @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<UserRole> roles;

  @OneToOne(mappedBy = "appUser")
  private Host host;

  public AppUser() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {

    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {

    this.password = password;
  }

  public Set<UserRole> getRoles() {
    return roles;
  }

  public void setRoles(Set<UserRole> roles) {
    this.roles = roles;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Host getHost() {
    return host;
  }

  public void setHost(Host host) {
    this.host = host;
  }
}
