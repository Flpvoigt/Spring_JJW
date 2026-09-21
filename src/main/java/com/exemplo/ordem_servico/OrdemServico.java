package com.exemplo.ordem_servico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class OrdemServico {

private static final double VALOR_HORA = 80.00;

    @NotBlank(message = "O nome do cliente é obrigatório")
    private String cliente;

    @NotBlank(message = "A descrição do serviço é obrigatória")
    private String descricaoServico;

    @Positive(message = "A quantidade de horas deve ser maior que zero")
    private double horasServico;

    @PositiveOrZero(message = "O custo dos materiais não pode ser negativo")
    private double custoMateriais;
    
    public OrdemServico() {
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public double getHorasServico() {
        return horasServico;
    }

    public void setHorasServico(double horasServico) {
        this.horasServico = horasServico;
    }

    public double getCustoMateriais() {
        return custoMateriais;
    }

    public void setCustoMateriais(double custoMateriais) {
        this.custoMateriais = custoMateriais;
    }

    public double getValorMaoDeObra() {
        return horasServico * VALOR_HORA;
    }

    public double getCustoTotal() {
        return getValorMaoDeObra() + custoMateriais;
    }
}