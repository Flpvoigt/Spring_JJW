package com.exemplo.ordem_servico;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaudacaoController {

    @GetMapping("/saudacao")
    public String saudacao() {
        return "Minha primeira API Spring Boot!";
    }
}