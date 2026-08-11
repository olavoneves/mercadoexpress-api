package br.com.fiap.mercadoexpress.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Corpo padronizado de erro devolvido pela API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroResposta {

    private LocalDateTime timestamp;
    private int status;
    private String erro;
    private String mensagem;
    private String caminho;

    /** Preenchido apenas nos erros de validacao (400). */
    private List<CampoInvalido> campos;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CampoInvalido {
        private String campo;
        private String mensagem;
    }
}
