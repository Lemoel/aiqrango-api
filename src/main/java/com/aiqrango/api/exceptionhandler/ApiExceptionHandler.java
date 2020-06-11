package com.aiqrango.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.aiqrango.domain.exception.EntidadeEmUsoException;
import com.aiqrango.domain.exception.EntidadeNaoEncontradaException;
import com.aiqrango.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e,
                                                                       WebRequest webRequest) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<Object> tratarEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest webRequest) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> tratarNegocioException(NegocioException e, WebRequest webRequest) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = ApiErrorMensagem.builder()
                    .dataHora(LocalDateTime.now())
                    .mensagem(status.getReasonPhrase()).build();
        } else if (body instanceof String) {
            body = ApiErrorMensagem.builder()
                    .dataHora(LocalDateTime.now())
                    .mensagem((String) body).build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
