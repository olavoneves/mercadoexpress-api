package br.com.fiap.mercadoexpress.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Centraliza o tratamento de erros da API, garantindo um corpo de resposta
 * unico e previsivel para 404, 400 e 500.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** 404 - recurso inexistente. */
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> tratarNaoEncontrado(RecursoNaoEncontradoException ex,
                                                            HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(montar(HttpStatus.NOT_FOUND, ex.getMessage(), request));
    }

    /** 400 - falha de validacao dos DTOs, com a lista de campos invalidos. */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> tratarValidacao(MethodArgumentNotValidException ex,
                                                        HttpServletRequest request) {
        List<ErroResposta.CampoInvalido> campos = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> ErroResposta.CampoInvalido.builder()
                        .campo(erro.getField())
                        .mensagem(erro.getDefaultMessage())
                        .build())
                .toList();

        ErroResposta corpo = montar(HttpStatus.BAD_REQUEST,
                "Um ou mais campos estao invalidos. Confira a lista em 'campos'.", request);
        corpo.setCampos(campos);

        return ResponseEntity.badRequest().body(corpo);
    }

    /** 400 - JSON malformado ou tipo de dado incompativel. */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResposta> tratarJsonInvalido(HttpMessageNotReadableException ex,
                                                           HttpServletRequest request) {
        return ResponseEntity
                .badRequest()
                .body(montar(HttpStatus.BAD_REQUEST,
                        "Corpo da requisicao invalido ou mal formatado.", request));
    }

    /** 500 - qualquer falha nao prevista. */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> tratarErroInterno(Exception ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(montar(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Erro interno no servidor: " + ex.getMessage(), request));
    }

    private ErroResposta montar(HttpStatus status, String mensagem, HttpServletRequest request) {
        return ErroResposta.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .erro(status.getReasonPhrase())
                .mensagem(mensagem)
                .caminho(request.getRequestURI())
                .build();
    }
}
