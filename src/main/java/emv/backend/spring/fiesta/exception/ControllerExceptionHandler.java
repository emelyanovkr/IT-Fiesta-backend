package emv.backend.spring.fiesta.exception;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

  private final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ExceptionResponse entityNotFound(EntityNotFoundException e) {
    LOGGER.error(e.getMessage());

    return new ExceptionResponse(
        LocalDateTime.now().format(formatter), e.getMessage(), HttpStatus.NOT_FOUND.value());
  }

  @ExceptionHandler(EntityAlreadyExistException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ExceptionResponse entityAlreadyExist(EntityAlreadyExistException e) {
    LOGGER.error(e.getMessage());

    return new ExceptionResponse(
        LocalDateTime.now().format(formatter), e.getMessage(), HttpStatus.CONFLICT.value());
  }

  @ExceptionHandler(BadCredentialsException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ExceptionResponse badCredentials(BadCredentialsException e) {
    LOGGER.error(e.getMessage());

    return new ExceptionResponse(
        LocalDateTime.now().format(formatter), e.getMessage(), HttpStatus.UNAUTHORIZED.value());
  }

  @ExceptionHandler(ForbiddenAccessException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ExceptionResponse forbiddenAccess(ForbiddenAccessException e) {
    LOGGER.error(e.getMessage());

    return new ExceptionResponse(
        LocalDateTime.now().format(formatter), e.getMessage(), HttpStatus.FORBIDDEN.value());
  }
}
