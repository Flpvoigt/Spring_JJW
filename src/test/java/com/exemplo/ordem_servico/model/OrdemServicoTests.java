package com.exemplo.ordem_servico.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.jupiter.api.Test;

class OrdemServicoTests {

    @Test
    void deveIniciarOrdemComStatusAberta() {
        OrdemServico ordem = new OrdemServico();

        assertEquals(StatusOrdemServico.ABERTA, ordem.getStatus());
    }

    @Test
    void deveRegistrarDatasDeCriacaoEAtualizacao() {
        OrdemServico ordem = new OrdemServico();

        ordem.registrarCriacao();

        assertEquals(ordem.getCriadaEm(), ordem.getAtualizadaEm());

        Instant criadaEm = ordem.getCriadaEm();
        ordem.registrarAtualizacao();

        assertEquals(criadaEm, ordem.getCriadaEm());
        assertFalse(ordem.getAtualizadaEm().isBefore(criadaEm));
    }

    @Test
    void deveCalcularValoresMonetariosComPrecisaoDecimal() {
        OrdemServico ordem = new OrdemServico();
        ordem.setHorasServico(new BigDecimal("1.15"));
        ordem.setCustoMateriais(new BigDecimal("0.10"));

        assertEquals(new BigDecimal("92.00"), ordem.getValorMaoDeObra());
        assertEquals(new BigDecimal("92.10"), ordem.getCustoTotal());
    }
}
