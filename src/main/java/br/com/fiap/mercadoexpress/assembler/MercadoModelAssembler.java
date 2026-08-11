package br.com.fiap.mercadoexpress.assembler;

import br.com.fiap.mercadoexpress.controller.MercadoController;
import br.com.fiap.mercadoexpress.dto.MercadoResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Responsavel por transformar o DTO de saida em um recurso HATEOAS.
 * <p>
 * E aqui que o nivel 3 de maturidade de Richardson e atendido: toda resposta
 * carrega os _links que descrevem as transicoes de estado possiveis
 * (self, all, update, patch, delete), sem que o cliente precise montar URLs.
 */
@Component
public class MercadoModelAssembler implements RepresentationModelAssembler<MercadoResponseDTO, EntityModel<MercadoResponseDTO>> {

    /**
     * Recurso individual: os cinco links exigidos pelo checkpoint.
     */
    @Override
    public EntityModel<MercadoResponseDTO> toModel(MercadoResponseDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(MercadoController.class).buscarPorId(dto.getId())).withSelfRel(),
                linkTo(methodOn(MercadoController.class).listarTodos()).withRel("all"),
                linkTo(methodOn(MercadoController.class).atualizar(dto.getId(), null)).withRel("update"),
                linkTo(methodOn(MercadoController.class).atualizarParcialmente(dto.getId(), null)).withRel("patch"),
                linkTo(methodOn(MercadoController.class).deletar(dto.getId())).withRel("delete"));
    }

    /**
     * Colecao: cada item ja vem com seus proprios _links e a colecao
     * ganha os links de navegacao (self, all) e de criacao (create).
     */
    @Override
    public CollectionModel<EntityModel<MercadoResponseDTO>> toCollectionModel(Iterable<? extends MercadoResponseDTO> itens) {
        List<EntityModel<MercadoResponseDTO>> modelos = new java.util.ArrayList<>();
        itens.forEach(item -> modelos.add(toModel(item)));

        return CollectionModel.of(modelos,
                linkTo(methodOn(MercadoController.class).listarTodos()).withSelfRel(),
                linkTo(methodOn(MercadoController.class).listarTodos()).withRel("all"),
                linkTo(methodOn(MercadoController.class).criar(null)).withRel("create"));
    }
}
