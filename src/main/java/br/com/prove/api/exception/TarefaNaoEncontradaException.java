package br.com.prove.api.exception;

public class TarefaNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public TarefaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public TarefaNaoEncontradaException(Long id) {
        this(String.format("Não existe um cadastro de tarefa com código %d", id));
    }

}
