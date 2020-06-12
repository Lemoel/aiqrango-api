package com.aiqrango.api.exceptionhandler;

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
    public ResponseEntity<Object> handlerEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e,
                                                                        WebRequest webRequest) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        String detail = e.getMessage();
        ApiErrorMessage apiErrorMessage = createApiErrorMessageBuilder(httpStatus, problemType, detail).build();
        return handleExceptionInternal(e, apiErrorMessage, new HttpHeaders(), httpStatus, webRequest);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<Object> handlerEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest webRequest) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
        String detail = e.getMessage();
        ApiErrorMessage apiErrorMessage = createApiErrorMessageBuilder(httpStatus, problemType, detail).build();
        return handleExceptionInternal(e, apiErrorMessage, new HttpHeaders(), httpStatus, webRequest);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handlerNegocioException(NegocioException e, WebRequest webRequest) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = e.getMessage();
        ApiErrorMessage apiErrorMessage = createApiErrorMessageBuilder(httpStatus, problemType, detail).build();
        return handleExceptionInternal(e, apiErrorMessage, new HttpHeaders(), httpStatus, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = ApiErrorMessage.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = ApiErrorMessage.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ApiErrorMessage.ApiErrorMessageBuilder createApiErrorMessageBuilder(HttpStatus status,
                                                                                ProblemType problemType,
                                                                                String detail) {
        return ApiErrorMessage.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }
}
