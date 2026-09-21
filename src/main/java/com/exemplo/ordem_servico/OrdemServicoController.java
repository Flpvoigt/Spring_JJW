package com.exemplo.ordem_servico;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ordens")
public class OrdemServicoController {

    @PostMapping
    public OrdemServico criar(@Valid @RequestBody OrdemServico ordem) {
        return ordem;
}
}