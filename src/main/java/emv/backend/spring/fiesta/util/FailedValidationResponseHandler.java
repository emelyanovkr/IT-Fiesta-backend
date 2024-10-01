package emv.backend.spring.fiesta.util;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.Map;

public class FailedValidationResponseHandler
{
  public static ResponseEntity<Map<String, String>> handleErrorMessaging(BindingResult bindingResult) {
    Map<String, String> errors = new HashMap<>();
    for (ObjectError error : bindingResult.getAllErrors()) {
      String fieldName = ((FieldError) error).getField();
      String errorMsg = error.getDefaultMessage();
      errors.put(fieldName, errorMsg);
    }
    return ResponseEntity.badRequest().body(errors);
  }
}
