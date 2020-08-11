package br.com.prove.api.exception;

public class ProcessoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public ProcessoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ProcessoNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de processo com código %d", id));
    }

}
