package com.exemplo.ordem_servico.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.exemplo.ordem_servico.model.OrdemServico;
import com.exemplo.ordem_servico.service.OrdemServicoService;

@ExtendWith(MockitoExtension.class)
class OrdemServicoControllerTests {

    @Mock
    private OrdemServicoService service;

    @InjectMocks
    private OrdemServicoController controller;

    @Test
    void deveRetornarCreatedComLocalizacaoDaNovaOrdem() {
        OrdemServico entrada = new OrdemServico();
        OrdemServico criada = new OrdemServico();
        criada.setId(7L);
        when(service.criar(entrada)).thenReturn(criada);

        ResponseEntity<OrdemServico> resposta = controller.criar(entrada);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(URI.create("/ordens/7"), resposta.getHeaders().getLocation());
        assertSame(criada, resposta.getBody());
    }
}
