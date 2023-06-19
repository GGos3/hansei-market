package xyz.ggos3.hanseimarket.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);

        return ResponseEntity.status(409).body(e.getMessage());
    }
}
