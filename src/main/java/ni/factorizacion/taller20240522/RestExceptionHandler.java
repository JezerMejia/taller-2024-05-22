package ni.factorizacion.taller20240522;

import jakarta.validation.ConstraintViolationException;
import ni.factorizacion.taller20240522.domain.dtos.GeneralResponse;
import ni.factorizacion.taller20240522.types.ControlException;
import ni.factorizacion.taller20240522.utils.ErrorTools;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ControlException.class)
    protected ResponseEntity<GeneralResponse<Object>> handleControlException(ControlException ex) {
        return GeneralResponse.getResponse(ex.getStatus(), ex.getMessage(), null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<GeneralResponse<Object>> handleViolation(ConstraintViolationException ex) {
        var errors = ex.getConstraintViolations();
        var errorsMap = ErrorTools.mapConstraintErrors(errors);
        return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Field errors", errorsMap);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<GeneralResponse<Map<String, List<String>>>> handleMethodArgumentsNotValid(MethodArgumentNotValidException ex) {
        var bindingResult = ex.getBindingResult();
        var errors = bindingResult.getFieldErrors();

        var errorsMap = ErrorTools.mapFieldErrors(errors);

        return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Field errors", errorsMap);
    }
}
