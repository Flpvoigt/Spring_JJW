package com.exemplo.ordem_servico.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exemplo.ordem_servico.exception.OrdemServicoNaoEncontradaException;
import com.exemplo.ordem_servico.model.OrdemServico;
import com.exemplo.ordem_servico.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepository repository;

    public OrdemServicoService(OrdemServicoRepository repository) {
        this.repository = repository;
    }

    public OrdemServico criar(OrdemServico ordem) {
        return repository.save(ordem);
    }

    public List<OrdemServico> listar() {
        return repository.findAll();
    }

    public OrdemServico buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new OrdemServicoNaoEncontradaException(id));
    }

    public void excluir(Long id) {
        OrdemServico ordem = buscarPorId(id);
        repository.delete(ordem);
    }

    public OrdemServico atualizar(Long id, OrdemServico novosDados) {
        OrdemServico ordem = buscarPorId(id);
        ordem.setCliente(novosDados.getCliente());
        ordem.setDescricaoServico(novosDados.getDescricaoServico());
        ordem.setHorasServico(novosDados.getHorasServico());
        ordem.setCustoMateriais(novosDados.getCustoMateriais());
        return repository.save(ordem);
    }
}
