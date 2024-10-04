package emv.backend.spring.fiesta.util;

import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class FailedValidationResponseHandler
{
  public static Map<String, String> handleErrorMessaging(BindingResult bindingResult) {
    Map<String, String> errors = new HashMap<>();
    for (ObjectError error : bindingResult.getAllErrors()) {
      String fieldName = ((FieldError) error).getField();
      String errorMsg = error.getDefaultMessage();
      errors.put(fieldName, errorMsg);
    }
    return errors;
  }
}
