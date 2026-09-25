package com.exemplo.ordem_servico.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exemplo.ordem_servico.exception.OrdemServicoNaoEncontradaException;
import com.exemplo.ordem_servico.model.OrdemServico;
import com.exemplo.ordem_servico.repository.OrdemServicoRepository;

@ExtendWith(MockitoExtension.class)
class OrdemServicoServiceTests {

    @Mock
    private OrdemServicoRepository repository;

    @InjectMocks
    private OrdemServicoService service;

    @Test
    void deveExcluirOrdemExistente() {
        OrdemServico ordem = new OrdemServico();
        ordem.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(ordem));

        service.excluir(1L);

        verify(repository).delete(ordem);
    }

    @Test
    void deveRetornarNaoEncontradoAoExcluirOrdemInexistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(
                OrdemServicoNaoEncontradaException.class,
                () -> service.excluir(99L));
    }
}
