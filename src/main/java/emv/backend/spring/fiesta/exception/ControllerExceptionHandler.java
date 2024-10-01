package emv.backend.spring.fiesta.exception;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class ControllerExceptionHandler {

  private final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ExceptionResponse> entityNotFound(EntityNotFoundException e) {
    LOGGER.error(e.getMessage());

    ExceptionResponse response =
        new ExceptionResponse(
            LocalDateTime.now().format(formatter), e.getMessage(), HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EntityAlreadyExistException.class)
  public ResponseEntity<ExceptionResponse> entityAlreadyExist(EntityAlreadyExistException e) {
    LOGGER.error(e.getMessage());

    ExceptionResponse response =
        new ExceptionResponse(
            LocalDateTime.now().format(formatter),
            e.getMessage(),
            HttpStatus.NOT_ACCEPTABLE.value());
    return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ExceptionResponse> badCredentials(BadCredentialsException e) {
    LOGGER.error(e.getMessage());

    ExceptionResponse response =
        new ExceptionResponse(
            LocalDateTime.now().format(formatter), e.getMessage(), HttpStatus.UNAUTHORIZED.value());
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }
}
