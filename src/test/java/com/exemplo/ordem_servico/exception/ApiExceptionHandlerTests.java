package com.exemplo.ordem_servico.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.exemplo.ordem_servico.model.StatusOrdemServico;

class ApiExceptionHandlerTests {

    private final ApiExceptionHandler handler = new ApiExceptionHandler();

    @Test
    void devePadronizarErroDeOrdemNaoEncontrada() {
        ProblemDetail problema = handler.tratarOrdemNaoEncontrada(
                new OrdemServicoNaoEncontradaException(42L));

        assertEquals(HttpStatus.NOT_FOUND.value(), problema.getStatus());
        assertEquals("Ordem de serviço não encontrada", problema.getTitle());
        assertEquals(
                "Não existe ordem de serviço com o ID 42",
                problema.getDetail());
    }

    @Test
    void deveInformarCamposComErroDeValidacao() {
        Object alvo = new Object();
        BeanPropertyBindingResult resultado = new BeanPropertyBindingResult(
                alvo,
                "ordemServico");
        resultado.addError(new FieldError(
                "ordemServico",
                "cliente",
                "O nome do cliente é obrigatório!"));
        MethodArgumentNotValidException exception =
                new MethodArgumentNotValidException(null, resultado);

        ProblemDetail problema = handler.tratarValidacao(exception);

        assertEquals(HttpStatus.BAD_REQUEST.value(), problema.getStatus());
        assertEquals("Dados inválidos", problema.getTitle());
        assertEquals(
                Map.of("cliente", "O nome do cliente é obrigatório!"),
                problema.getProperties().get("campos"));
    }

    @Test
    void devePadronizarErroDeTransicaoDeStatus() {
        ProblemDetail problema = handler.tratarTransicaoStatusInvalida(
                new TransicaoStatusInvalidaException(
                        StatusOrdemServico.ABERTA,
                        StatusOrdemServico.CONCLUIDA));

        assertEquals(HttpStatus.CONFLICT.value(), problema.getStatus());
        assertEquals("Transição de status inválida", problema.getTitle());
        assertEquals(
                "Não é permitido alterar o status de ABERTA para CONCLUIDA",
                problema.getDetail());
    }
}
