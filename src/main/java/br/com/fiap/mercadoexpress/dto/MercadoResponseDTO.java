package br.com.fiap.mercadoexpress.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Payload de saida da API. E envelopado em EntityModel/CollectionModel
 * pelo assembler para receber os _links do HATEOAS.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MercadoResponseDTO {

    private Long id;
    private String nome;
    private String tipo;
    private String setor;
    private String tamanho;
    private BigDecimal preco;
}
