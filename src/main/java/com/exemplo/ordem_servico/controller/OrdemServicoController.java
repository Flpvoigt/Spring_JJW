package com.exemplo.ordem_servico.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.exemplo.ordem_servico.model.OrdemServico;
import com.exemplo.ordem_servico.service.OrdemServicoService;

import jakarta.validation.Valid;
import java.util.List;

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
    public List<OrdemServico> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public OrdemServico buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
}
}
