package com.aiqrango.api.exceptionhandler;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
public class ApiErrorMensagem {
    LocalDateTime dataHora;
    String mensagem;
}