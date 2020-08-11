package br.com.prove.api.exception;

public class CargoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public CargoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public CargoNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de cargo com código %d", id));
    }

}
