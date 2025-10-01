package com.monicah.Umoja_Welfare.Exceptions;

import com.monicah.Umoja_Welfare.DTO.BaseApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MemberExistException.class)
    public ResponseEntity<BaseApiResponse> handleMemberExistException(MemberExistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BaseApiResponse(false, 404, ex.getMessage(), null));
    }
    @ExceptionHandler(RegisterExistException.class)
    public ResponseEntity<BaseApiResponse> handleRegisterExistException(RegisterExistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BaseApiResponse(false, 404, ex.getMessage(), null));
    }
    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<?> handleUserExist(UserExistException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of(
                        "status", HttpStatus.CONFLICT.value(),
                        "message", "User already exists",
                        "error", ex.getMessage()
                )
        );
    }
    // this one handles the validations such as Invalid email format, password: must not be blank
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(new BaseApiResponse(false, 400, errorMsg, null));
    }

}
