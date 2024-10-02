package emv.backend.spring.fiesta.model.userAccount;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserRoleKey implements Serializable {

  @Column(name = "user_id")
  private Integer user_id;

  @Column(name = "role_id")
  private Integer role_id;

  public UserRoleKey() {}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserRoleKey that = (UserRoleKey) o;
    return Objects.equals(user_id, that.user_id) && Objects.equals(role_id, that.role_id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user_id, role_id);
  }
}
