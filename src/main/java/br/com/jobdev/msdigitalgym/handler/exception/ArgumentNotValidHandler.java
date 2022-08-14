package br.com.jobdev.msdigitalgym.handler.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class ArgumentNotValidHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String error = "Validation error";

        String datetime = String.valueOf(LocalDateTime.now());

        ArrayList<Map<String, String>> details = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(detail -> {
            Map<String, String> map = Map.of("field", detail.getField(), "code", Objects.requireNonNull(detail.getCode()));
            details.add(map);
        });

        Map<String, Serializable> res = Map.of("datetime", datetime, "status", HttpStatus.BAD_REQUEST.value(), "error", error, "details", details);

        log.error("Error validating request", ex);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

}