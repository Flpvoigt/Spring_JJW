package com.exemplo.ordem_servico.exception;

import com.exemplo.ordem_servico.model.StatusOrdemServico;

public class TransicaoStatusInvalidaException extends RuntimeException {

    public TransicaoStatusInvalidaException(
            StatusOrdemServico statusAtual,
            StatusOrdemServico novoStatus) {
        super("Não é permitido alterar o status de "
                + statusAtual
                + " para "
                + novoStatus);
    }
}
