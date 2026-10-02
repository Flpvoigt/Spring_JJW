package com.exemplo.ordem_servico.dto;

import com.exemplo.ordem_servico.model.StatusOrdemServico;

import jakarta.validation.constraints.NotNull;

public record AtualizarStatusRequest(
        @NotNull(message = "O status é obrigatório")
        StatusOrdemServico status) {
}
