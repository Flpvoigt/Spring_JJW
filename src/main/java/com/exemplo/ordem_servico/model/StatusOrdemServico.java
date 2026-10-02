package com.exemplo.ordem_servico.model;

public enum StatusOrdemServico {
    ABERTA,
    EM_ANDAMENTO,
    CONCLUIDA,
    CANCELADA;

    public boolean podeTransicionarPara(StatusOrdemServico proximoStatus) {
        if (this == proximoStatus) {
            return true;
        }

        return switch (this) {
            case ABERTA -> proximoStatus == EM_ANDAMENTO
                    || proximoStatus == CANCELADA;
            case EM_ANDAMENTO -> proximoStatus == CONCLUIDA
                    || proximoStatus == CANCELADA;
            case CONCLUIDA, CANCELADA -> false;
        };
    }
}
