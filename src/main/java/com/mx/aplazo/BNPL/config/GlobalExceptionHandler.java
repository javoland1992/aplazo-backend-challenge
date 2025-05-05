package com.mx.aplazo.BNPL.config;

import com.mx.aplazo.BNPL.exception.InvalidAgeException;
import com.mx.aplazo.BNPL.exception.InvalidCreditLineException;
import com.mx.aplazo.BNPL.exception.InvalidDateInput;
import com.mx.aplazo.BNPL.exception.NotFoundCustomerException;
import com.mx.aplazo.BNPL.exception.NotFoundLoanException;
import com.mx.aplazo.BNPL.util.GeneralPurpose;
import jakarta.servlet.http.HttpServletRequest;
import org.antlr.v4.runtime.atn.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("code", "APZ000002");
        errors.put("error", "INVALID_CUSTOMER_REQUEST");
        errors.put("timestamp", LocalDateTime.now().toInstant(ZoneOffset.UTC).getEpochSecond());
        Map<String, String> message = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                message.put(error.getField(), error.getDefaultMessage()));
        errors.put("message", message);
        errors.put("path",request.getRequestURI());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(InvalidAgeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleInvalidAgeException(
            InvalidAgeException ex, HttpServletRequest request) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("code", "APZ000002");
        errors.put("error", "INVALID_CUSTOMER_REQUEST");
        errors.put("timestamp", LocalDateTime.now().toInstant(ZoneOffset.UTC).getEpochSecond());
        errors.put("message", ex.getMessage());
        errors.put("path",request.getRequestURI());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NotFoundCustomerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleNotFoundCutomerException(
            NotFoundCustomerException ex, HttpServletRequest request) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("code", "APZ000004");
        errors.put("error", "INVALID_REQUEST");
        errors.put("timestamp", LocalDateTime.now().toInstant(ZoneOffset.UTC).getEpochSecond());
        errors.put("message", ex.getMessage());
        String pathPrint = GeneralPurpose.removeLastSegment(request.getRequestURI()) + "/invalid-uuid";
        errors.put("path",pathPrint);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(InvalidDateInput.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleInvalidDateInput(
            InvalidDateInput ex, HttpServletRequest request) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("code", "APZ000002");
        errors.put("error", "INVALID_CUSTOMER_REQUEST");
        errors.put("timestamp", LocalDateTime.now().toInstant(ZoneOffset.UTC).getEpochSecond());
        errors.put("message", ex.getMessage());
        errors.put("path",request.getRequestURI());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(InvalidCreditLineException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleInvalidCreditLineException(
            InvalidCreditLineException ex, HttpServletRequest request) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("code", "APZ000006");
        errors.put("error", "INVALID_LOAN_REQUEST");
        errors.put("timestamp", LocalDateTime.now().toInstant(ZoneOffset.UTC).getEpochSecond());
        errors.put("message", ex.getMessage());
        errors.put("path",request.getRequestURI());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<Object> handleGenericException(HttpServletRequest request, Exception ex) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("code", "APZ000001");
        errors.put("error", "INTERNAL_SERVER_ERROR");
        errors.put("timestamp", LocalDateTime.now().toInstant(ZoneOffset.UTC).getEpochSecond());
        errors.put("message", ex.getMessage());
        String pathPrint = GeneralPurpose.removeLastSegment(request.getRequestURI()) + "/current-path-that-threw-error";
        errors.put("path",pathPrint);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NotFoundLoanException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleNotFoundLoanException(
            NotFoundLoanException ex, HttpServletRequest request) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("code", "APZ000004");
        errors.put("error", "INVALID_REQUEST");
        errors.put("timestamp", LocalDateTime.now().toInstant(ZoneOffset.UTC).getEpochSecond());
        errors.put("message", ex.getMessage());
        String pathPrint = GeneralPurpose.removeLastSegment(request.getRequestURI()) + "/invalid-uuid";
        errors.put("path",pathPrint);
        return ResponseEntity.badRequest().body(errors);
    }
}