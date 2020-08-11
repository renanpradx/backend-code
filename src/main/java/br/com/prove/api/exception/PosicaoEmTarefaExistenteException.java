package br.com.prove.api.exception;

public class PosicaoEmTarefaExistenteException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public PosicaoEmTarefaExistenteException(String mensagem) {
		super(mensagem);
	}
	
}
