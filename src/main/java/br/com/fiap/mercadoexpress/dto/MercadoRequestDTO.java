package br.com.fiap.mercadoexpress.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Payload de entrada usado no POST e no PUT.
 * Todos os campos obrigatorios sao validados antes de chegar ao service.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MercadoRequestDTO {

    @NotBlank(message = "O nome do produto e obrigatorio")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O tipo do produto e obrigatorio")
    @Size(max = 50, message = "O tipo deve ter no maximo 50 caracteres")
    private String tipo;

    @NotBlank(message = "O setor do produto e obrigatorio")
    @Size(max = 50, message = "O setor deve ter no maximo 50 caracteres")
    private String setor;

    @Size(max = 30, message = "O tamanho deve ter no maximo 30 caracteres")
    private String tamanho;

    @NotNull(message = "O preco e obrigatorio")
    @PositiveOrZero(message = "O preco deve ser maior ou igual a zero")
    private BigDecimal preco;
}
