package br.com.fiap.mercadoexpress.controller;

import br.com.fiap.mercadoexpress.assembler.MercadoModelAssembler;
import br.com.fiap.mercadoexpress.dto.MercadoPatchDTO;
import br.com.fiap.mercadoexpress.dto.MercadoRequestDTO;
import br.com.fiap.mercadoexpress.dto.MercadoResponseDTO;
import br.com.fiap.mercadoexpress.service.MercadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoints REST do mercado express.
 * <p>
 * Todas as respostas passam pelo {@link MercadoModelAssembler}, portanto
 * sempre trazem o bloco {@code _links} (HATEOAS - nivel 3 de Richardson).
 */
@RestController
@RequestMapping("/mercado")
@RequiredArgsConstructor
public class MercadoController {

    private final MercadoService service;
    private final MercadoModelAssembler assembler;

    /**
     * GET /mercado - lista todos os itens. 200 OK.
     */
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<MercadoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(assembler.toCollectionModel(service.listarTodos()));
    }

    /**
     * GET /mercado/{id} - busca um item. 200 OK ou 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<MercadoResponseDTO>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(assembler.toModel(service.buscarPorId(id)));
    }

    /**
     * POST /mercado - cria um item. 201 Created com header Location.
     */
    @PostMapping
    public ResponseEntity<EntityModel<MercadoResponseDTO>> criar(@Valid @RequestBody MercadoRequestDTO dto) {
        EntityModel<MercadoResponseDTO> recurso = assembler.toModel(service.criar(dto));
        return ResponseEntity
                .created(recurso.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(recurso);
    }

    /**
     * PUT /mercado/{id} - substitui o item inteiro. 200 OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<MercadoResponseDTO>> atualizar(@PathVariable Long id,
                                                                     @Valid @RequestBody MercadoRequestDTO dto) {
        return ResponseEntity.ok(assembler.toModel(service.atualizar(id, dto)));
    }

    /**
     * PATCH /mercado/{id} - atualiza somente os campos enviados. 200 OK.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<MercadoResponseDTO>> atualizarParcialmente(@PathVariable Long id,
                                                                                 @Valid @RequestBody MercadoPatchDTO dto) {
        return ResponseEntity.ok(assembler.toModel(service.atualizarParcialmente(id, dto)));
    }

    /**
     * DELETE /mercado/{id} - remove o item. 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
