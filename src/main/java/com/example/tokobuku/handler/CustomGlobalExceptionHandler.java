package com.example.tokobuku.handler;
import com.example.tokobuku.exception.BookNotFoundException;
import com.example.tokobuku.exception.BookUnsupportedFieldPatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    //METHOD UNTK MENANGANI ERROR YG TIDAK ADA HANDLERNYA
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }
    @ExceptionHandler(BookNotFoundException.class)
    public void handleBukuTidakKetemu(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());

    }
    @ExceptionHandler(BookUnsupportedFieldPatchException.class)
    public void handleBukuTidakBisaDiUpdate(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.METHOD_NOT_ALLOWED.value());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void handlePelanggaranConstraint(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("timestamp",new Date());
        body.put("status",status.value());

        List<String> errors=ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x->x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors",errors);
        return new ResponseEntity<>(body,headers,status);
    }
}
