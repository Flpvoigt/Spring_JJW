package com.exemplo.ordem_servico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.ordem_servico.model.OrdemServico;

@Repository
public interface OrdemServicoRepository
        extends JpaRepository<OrdemServico, Long> {

    List<OrdemServico> findByClienteContainingIgnoreCaseOrderByClienteAscIdAsc(
            String cliente);
}
