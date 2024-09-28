package emv.backend.spring.fiesta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AuthenticationDTO {

  @Size(max = 35, message = "Too long username, 35 max")
  @NotBlank(message = "Username can't be blank")
  @NotNull(message = "Username can't be null")
  public String username;

  @Size(max = 50, message = "Too long password, 50 max")
  @NotBlank(message = "Password can't be blank")
  @NotNull(message = "Password can't be null")
  public String password;

  public AuthenticationDTO() {}

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
}
