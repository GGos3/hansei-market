package xyz.ggos3.hanseimarket.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.ggos3.hanseimarket.dto.ApiResponse;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse illegalArgumentExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);

        return ApiResponse.error(e.getMessage());
    }
}
