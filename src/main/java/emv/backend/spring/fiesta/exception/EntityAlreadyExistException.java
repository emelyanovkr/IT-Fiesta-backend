package emv.backend.spring.fiesta.exception;

public class EntityAlreadyExistException extends RuntimeException {

  public EntityAlreadyExistException(String message) {
    super(message);
  }
}
