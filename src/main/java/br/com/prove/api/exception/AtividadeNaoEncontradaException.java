package br.com.prove.api.exception;

public class AtividadeNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public AtividadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public AtividadeNaoEncontradaException(Long id) {
        this(String.format("Não existe um cadastro de atividade com código %d", id));
    }

}
