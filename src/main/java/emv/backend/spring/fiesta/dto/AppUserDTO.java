package emv.backend.spring.fiesta.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AppUserDTO {

  private int id;

  @NotBlank(message = "Username can't be blank")
  @NotNull(message = "Username can't be null")
  private String username;

  @NotBlank(message = "Password can't be blank")
  @NotNull(message = "Password can't be null")
  private String password;

  @Email(message = "Please, check your email meets requirements")
  @NotBlank(message = "Email can't be blank")
  @NotNull(message = "Email can't be null")
  private String email;

  public AppUserDTO() {}

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
