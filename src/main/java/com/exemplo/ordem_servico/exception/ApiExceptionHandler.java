package com.exemplo.ordem_servico.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(OrdemServicoNaoEncontradaException.class)
    public ProblemDetail tratarOrdemNaoEncontrada(
            OrdemServicoNaoEncontradaException exception) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage());
        problema.setTitle("Ordem de serviço não encontrada");
        return problema;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail tratarValidacao(
            MethodArgumentNotValidException exception) {
        Map<String, String> campos = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage,
                        (primeiraMensagem, mensagemRepetida) -> primeiraMensagem,
                        LinkedHashMap::new));

        ProblemDetail problema = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Um ou mais campos possuem valores inválidos.");
        problema.setTitle("Dados inválidos");
        problema.setProperty("campos", campos);
        return problema;
    }
}
