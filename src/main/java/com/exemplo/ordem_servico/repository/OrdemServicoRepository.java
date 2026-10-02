package com.exemplo.ordem_servico.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.ordem_servico.model.OrdemServico;

@Repository
public interface OrdemServicoRepository
        extends JpaRepository<OrdemServico, Long> {

    Page<OrdemServico> findByClienteContainingIgnoreCase(
            String cliente,
            Pageable pageable);
}
