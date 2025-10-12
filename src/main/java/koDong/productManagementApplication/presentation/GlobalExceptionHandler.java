package koDong.productManagementApplication.presentation;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final View error;

    public GlobalExceptionHandler(View error) {
        this.error = error;
    }

    // 이 코드는 전혀 모르겠다...!!!!
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleConstraintViolatedException(
            ConstraintViolationException ex
    ) {
        Set<ConstraintViolation<?>> constraintViolationExceptions = ex.getConstraintViolations();
        List<String> errors = constraintViolationExceptions.stream()
                .map(
                        constraintViolationException ->
                                constraintViolationException.getPropertyPath() + ", " +
                                        constraintViolationException.getMessage()
                )
                .toList();
        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errors = fieldErrors.stream()
                .map(
                        fieldError ->
                                fieldError.getField() + ", " + fieldError.getDefaultMessage()
                )
                .toList();
        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

    }

    private String extractField(Path path) {
        String[] splitArray = path.toString().split("[.]");
        int lastIndex = splitArray.length - 1;
        return splitArray[lastIndex];
    }


}



