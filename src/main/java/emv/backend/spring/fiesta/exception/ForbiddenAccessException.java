package emv.backend.spring.fiesta.exception;

public class ForbiddenAccessException extends DatabaseManagementException {

  public ForbiddenAccessException(String message) {
    super(message);
  }
}
