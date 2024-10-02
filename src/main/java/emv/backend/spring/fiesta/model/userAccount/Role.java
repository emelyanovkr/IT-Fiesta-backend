package emv.backend.spring.fiesta.model.userAccount;

import jakarta.persistence.*;

@Entity
@Table(
    schema = "user_account_schema",
    name = "roles",
    uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(name = "name")
  private RoleType name;

  public Role() {}

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public RoleType getName() {
    return name;
  }

  public void setName(RoleType name) {
    this.name = name;
  }
}
