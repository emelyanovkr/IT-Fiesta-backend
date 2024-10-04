package emv.backend.spring.fiesta.exception;

public record ExceptionResponse(String timestamp, String message, int statusCode) {}
