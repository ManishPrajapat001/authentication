package org.manish.authentication.handler;

import org.manish.authentication.dto.ApiResponse;
import org.manish.authentication.exception.InvalidPasswordException;
import org.manish.authentication.exception.UserAlreadyExistsException;
import org.manish.authentication.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> handleUserNotFoundException (UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, e.getMessage(),null));

    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiResponse> handleInvalidPasswordException (InvalidPasswordException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(false, e.getMessage(),null));

    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handleUserAlreadyExistsException (UserAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(false, e.getMessage(),null));

    }

//   validation error/exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> FieldErrors = e.getBindingResult().getFieldErrors();
        List<String> errors  = new ArrayList<>();
        for(FieldError error : FieldErrors){
            errors.add(error.getDefaultMessage());
        }
        String errorMessages = String.join(", ", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, errorMessages,null));
    }

//    if anything goes wrong
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Something went wrong",null));
    }
}
