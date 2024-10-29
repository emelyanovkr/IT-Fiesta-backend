package emv.backend.spring.fiesta.exception;

public abstract class DatabaseManagementException extends RuntimeException {
  public DatabaseManagementException(String message) {
    super(message);
  }
}
