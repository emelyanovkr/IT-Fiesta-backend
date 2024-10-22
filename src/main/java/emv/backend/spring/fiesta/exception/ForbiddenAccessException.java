package emv.backend.spring.fiesta.exception;

public class ForbiddenAccessException extends RuntimeException {

  public ForbiddenAccessException(String message) {
    super(message);
  }
}
