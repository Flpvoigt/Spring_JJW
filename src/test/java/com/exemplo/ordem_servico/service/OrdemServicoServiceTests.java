package com.exemplo.ordem_servico.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
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
    void deveListarTodasAsOrdensQuandoClienteNaoForInformado() {
        List<OrdemServico> ordens = List.of(novaOrdem(
                "Cliente", "Servico", "1", "0"));
        when(repository.findAll()).thenReturn(ordens);

        assertEquals(ordens, service.listar(null));
        verify(repository).findAll();
    }

    @Test
    void deveBuscarOrdensPorTrechoDoNomeDoCliente() {
        List<OrdemServico> ordens = List.of(novaOrdem(
                "Maria Silva", "Servico", "1", "0"));
        when(repository.findByClienteContainingIgnoreCaseOrderByClienteAscIdAsc(
                "maria"))
                .thenReturn(ordens);

        assertEquals(ordens, service.listar("  maria  "));
        verify(repository)
                .findByClienteContainingIgnoreCaseOrderByClienteAscIdAsc(
                        "maria");
    }

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

    @Test
    void deveAtualizarOrdemExistente() {
        OrdemServico ordem = novaOrdem("Cliente antigo", "Servico antigo", "1", "10");
        ordem.setId(1L);
        OrdemServico novosDados = novaOrdem("Cliente novo", "Servico novo", "2", "25");
        when(repository.findById(1L)).thenReturn(Optional.of(ordem));
        when(repository.save(ordem)).thenReturn(ordem);

        OrdemServico atualizada = service.atualizar(1L, novosDados);

        assertEquals(1L, atualizada.getId());
        assertEquals("Cliente novo", atualizada.getCliente());
        assertEquals("Servico novo", atualizada.getDescricaoServico());
        assertEquals(new BigDecimal("2"), atualizada.getHorasServico());
        assertEquals(new BigDecimal("25"), atualizada.getCustoMateriais());
        verify(repository).save(ordem);
    }

    @Test
    void deveRetornarNaoEncontradoAoAtualizarOrdemInexistente() {
        OrdemServico novosDados = novaOrdem("Cliente", "Servico", "1", "0");
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(
                OrdemServicoNaoEncontradaException.class,
                () -> service.atualizar(99L, novosDados));
    }

    private OrdemServico novaOrdem(
            String cliente,
            String descricao,
            String horas,
            String materiais) {
        OrdemServico ordem = new OrdemServico();
        ordem.setCliente(cliente);
        ordem.setDescricaoServico(descricao);
        ordem.setHorasServico(new BigDecimal(horas));
        ordem.setCustoMateriais(new BigDecimal(materiais));
        return ordem;
    }
}
