package emv.backend.spring.fiesta.model.userSchema;

public enum RoleType {
  GUEST("GUEST"),
  USER("USER"),
  HOST("HOST"),
  ADMIN("ADMIN");

  private final String representation;

  RoleType(String representation) {
    this.representation = representation;
  }

  public String getRepresentation() {
    return representation;
  }
}
