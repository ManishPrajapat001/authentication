package org.manish.authentication.handler;

import org.manish.authentication.dto.ApiResponse;
import org.manish.authentication.exception.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handleUserAlreadyExistsException (UserAlreadyExistsException e) {
//        return ;
        return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage(),null));

    }

}
