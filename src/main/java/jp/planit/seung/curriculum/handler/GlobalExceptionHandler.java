package jp.planit.seung.curriculum.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletResponse;
import jp.planit.seung.curriculum.dto.base.BaseErrorResponse;
import jp.planit.seung.curriculum.exception.CustomException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ CustomException.class })
    protected ResponseEntity<?> handleCustomException(Exception ex) {
        BaseErrorResponse res = new BaseErrorResponse();
        res.setError(HttpStatus.METHOD_NOT_ALLOWED);
        res.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(res);
    }

    @ExceptionHandler({ Exception.class })
    protected void handleServerException(Exception ex, HttpServletResponse response) throws IOException {
        String redirect_uri = "/error";
        response.sendRedirect(redirect_uri);
    }
}