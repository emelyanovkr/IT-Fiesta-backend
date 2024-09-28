package emv.backend.spring.fiesta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(schema = "user_account_schema", name = "user_data")
public class AppUser
{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank(message = "Username can't be blank")
  @NotNull(message = "Username can't be null")
  @Column(name = "username")
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

  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private Role role;

  public AppUser() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
