package com.busanit501.travelproject.advice;

import com.busanit501.travelproject.exception.member.UnauthorizedException;
import com.busanit501.travelproject.exception.member.UnauthorizedRestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@RestControllerAdvice
public class UnauthorizedRestExceptionHandler {

    @ExceptionHandler(UnauthorizedRestException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorizedException(UnauthorizedRestException ex, RedirectAttributes redirectAttributes) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
          Map.of("success", false, "message", "access denied")
      );
    }
}
