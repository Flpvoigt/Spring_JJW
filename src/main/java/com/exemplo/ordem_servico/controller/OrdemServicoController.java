package com.exemplo.ordem_servico.controller;

import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exemplo.ordem_servico.dto.AtualizarStatusRequest;
import com.exemplo.ordem_servico.model.OrdemServico;
import com.exemplo.ordem_servico.service.OrdemServicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ordens")
public class OrdemServicoController {

    private final OrdemServicoService service;

    public OrdemServicoController(OrdemServicoService service) {
        this.service = service;
    }

    @PostMapping
    public OrdemServico criar(@Valid @RequestBody OrdemServico ordem) {
        return service.criar(ordem);
    }

    @GetMapping
    public Page<OrdemServico> listar(
            @RequestParam(required = false) String cliente,
            @PageableDefault(size = 20, sort = "cliente") Pageable pageable) {
        return service.listar(cliente, pageable);
    }

    @GetMapping("/{id}")
    public OrdemServico buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @PutMapping("/{id}")
    public OrdemServico atualizar(
            @PathVariable Long id,
            @Valid @RequestBody OrdemServico ordem) {
        return service.atualizar(id, ordem);
    }

    @PatchMapping("/{id}/status")
    public OrdemServico atualizarStatus(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarStatusRequest requisicao) {
        return service.atualizarStatus(id, requisicao.status());
    }
}
