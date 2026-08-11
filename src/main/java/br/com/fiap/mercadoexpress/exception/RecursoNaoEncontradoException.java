package br.com.fiap.mercadoexpress.exception;

/**
 * Lancada quando um item do mercado nao existe no banco.
 * Traduzida para HTTP 404 pelo {@code GlobalExceptionHandler}.
 */
public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public static RecursoNaoEncontradoException paraId(Long id) {
        return new RecursoNaoEncontradoException("Nenhum item do mercado encontrado com o id " + id);
    }
}
