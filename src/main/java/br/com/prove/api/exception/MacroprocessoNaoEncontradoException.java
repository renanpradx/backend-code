package br.com.prove.api.exception;

public class MacroprocessoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public MacroprocessoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public MacroprocessoNaoEncontradoException(Long id) {
		this(String.format("Não existe um cadastro de macroprocesso com código %d", id));
	}
	
}
