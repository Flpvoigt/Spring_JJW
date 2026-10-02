package com.exemplo.ordem_servico.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class OrdemServicoTests {

    @Test
    void deveCalcularValoresMonetariosComPrecisaoDecimal() {
        OrdemServico ordem = new OrdemServico();
        ordem.setHorasServico(new BigDecimal("1.15"));
        ordem.setCustoMateriais(new BigDecimal("0.10"));

        assertEquals(new BigDecimal("92.00"), ordem.getValorMaoDeObra());
        assertEquals(new BigDecimal("92.10"), ordem.getCustoTotal());
    }
}
