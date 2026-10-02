package com.exemplo.ordem_servico.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordens_servico")
public class OrdemServico {

    private static final BigDecimal VALOR_HORA = new BigDecimal("80.00");
    private static final int ESCALA_MONETARIA = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do cliente é obrigatório!")
    private String cliente;

    @NotBlank(message = "A descrição do serviço é obrigatória")
    private String descricaoServico;
    

    @Positive(message = "A quantidade de horas deve ser maior que zero")
    @Column(precision = 8, scale = 2)
    private BigDecimal horasServico;

    @PositiveOrZero(message = "O custo dos materiais não pode ser negativo")
    @Column(precision = 12, scale = 2)
    private BigDecimal custoMateriais;

    @NotNull(message = "O status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StatusOrdemServico status = StatusOrdemServico.ABERTA;

    @Column(updatable = false)
    private Instant criadaEm;

    private Instant atualizadaEm;
    

    public Long getId() {
    return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getHorasServico() {
        return horasServico;
    }

    public void setHorasServico(BigDecimal horasServico) {
        this.horasServico = horasServico;
    }

    public BigDecimal getCustoMateriais() {
        return custoMateriais;
    }

    public void setCustoMateriais(BigDecimal custoMateriais) {
        this.custoMateriais = custoMateriais;
    }

    public StatusOrdemServico getStatus() {
        return status == null ? StatusOrdemServico.ABERTA : status;
    }

    public void setStatus(StatusOrdemServico status) {
        this.status = status;
    }

    public Instant getCriadaEm() {
        return criadaEm;
    }

    public Instant getAtualizadaEm() {
        return atualizadaEm;
    }

    @PrePersist
    void registrarCriacao() {
        Instant agora = Instant.now();
        criadaEm = agora;
        atualizadaEm = agora;
    }

    @PreUpdate
    void registrarAtualizacao() {
        atualizadaEm = Instant.now();
    }

    public BigDecimal getValorMaoDeObra() {
        return horasServico.multiply(VALOR_HORA)
                .setScale(ESCALA_MONETARIA, RoundingMode.HALF_UP);
    }

    public BigDecimal getCustoTotal() {
        return getValorMaoDeObra().add(custoMateriais)
                .setScale(ESCALA_MONETARIA, RoundingMode.HALF_UP);
    }
}
