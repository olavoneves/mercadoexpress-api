package br.com.fiap.mercadoexpress.dto;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Payload de entrada do PATCH (atualizacao parcial).
 * Nenhum campo e obrigatorio: apenas os campos enviados (nao nulos) sao aplicados.
 * As validacoes de formato continuam valendo para os campos que forem enviados.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MercadoPatchDTO {

    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @Size(max = 60, message = "O tipo deve ter no maximo 60 caracteres")
    private String tipo;

    @Size(max = 60, message = "O setor deve ter no maximo 60 caracteres")
    private String setor;

    @Size(max = 30, message = "O tamanho deve ter no maximo 30 caracteres")
    private String tamanho;

    @PositiveOrZero(message = "O preco deve ser maior ou igual a zero")
    private BigDecimal preco;
}
