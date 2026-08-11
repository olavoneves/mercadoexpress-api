package br.com.fiap.mercadoexpress.service;

import br.com.fiap.mercadoexpress.dto.MercadoPatchDTO;
import br.com.fiap.mercadoexpress.dto.MercadoRequestDTO;
import br.com.fiap.mercadoexpress.dto.MercadoResponseDTO;
import br.com.fiap.mercadoexpress.exception.RecursoNaoEncontradoException;
import br.com.fiap.mercadoexpress.model.Mercado;
import br.com.fiap.mercadoexpress.repository.MercadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Regras de negocio do mercado express.
 * Concentra a conversao entre DTO e entidade, deixando o controller apenas com o HTTP.
 */
@Service
@RequiredArgsConstructor
public class MercadoService {

    private final MercadoRepository repository;

    @Transactional(readOnly = true)
    public List<MercadoResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::paraResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public MercadoResponseDTO buscarPorId(Long id) {
        return paraResponse(buscarEntidade(id));
    }

    @Transactional
    public MercadoResponseDTO criar(MercadoRequestDTO dto) {
        Mercado novo = Mercado.builder()
                .nome(dto.getNome())
                .tipo(dto.getTipo())
                .setor(dto.getSetor())
                .tamanho(dto.getTamanho())
                .preco(dto.getPreco())
                .build();
        return paraResponse(repository.save(novo));
    }

    /**
     * PUT: substitui integralmente o recurso. Campos ausentes viram nulos.
     */
    @Transactional
    public MercadoResponseDTO atualizar(Long id, MercadoRequestDTO dto) {
        Mercado existente = buscarEntidade(id);
        existente.setNome(dto.getNome());
        existente.setTipo(dto.getTipo());
        existente.setSetor(dto.getSetor());
        existente.setTamanho(dto.getTamanho());
        existente.setPreco(dto.getPreco());
        return paraResponse(repository.save(existente));
    }

    /**
     * PATCH: aplica somente os campos nao nulos do payload, preservando o restante.
     */
    @Transactional
    public MercadoResponseDTO atualizarParcialmente(Long id, MercadoPatchDTO dto) {
        Mercado existente = buscarEntidade(id);
        if (dto.getNome() != null) {
            existente.setNome(dto.getNome());
        }
        if (dto.getTipo() != null) {
            existente.setTipo(dto.getTipo());
        }
        if (dto.getSetor() != null) {
            existente.setSetor(dto.getSetor());
        }
        if (dto.getTamanho() != null) {
            existente.setTamanho(dto.getTamanho());
        }
        if (dto.getPreco() != null) {
            existente.setPreco(dto.getPreco());
        }
        return paraResponse(repository.save(existente));
    }

    @Transactional
    public void deletar(Long id) {
        repository.delete(buscarEntidade(id));
    }

    private Mercado buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> RecursoNaoEncontradoException.paraId(id));
    }

    private MercadoResponseDTO paraResponse(Mercado mercado) {
        return MercadoResponseDTO.builder()
                .id(mercado.getId())
                .nome(mercado.getNome())
                .tipo(mercado.getTipo())
                .setor(mercado.getSetor())
                .tamanho(mercado.getTamanho())
                .preco(mercado.getPreco())
                .build();
    }
}
