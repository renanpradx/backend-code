package br.com.prove.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.prove.api.model.Processo;
import br.com.prove.api.enumarations.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityRequest {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private Long posicao;
	
	@NotNull
	private StatusEnum status;

	@NotNull
	private Processo processo;

}
