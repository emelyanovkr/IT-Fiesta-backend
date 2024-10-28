package emv.backend.spring.fiesta.exception;

public class EntityAlreadyExistException extends DatabaseManagementException {

  public EntityAlreadyExistException(String message) {
    super(message);
  }
}
