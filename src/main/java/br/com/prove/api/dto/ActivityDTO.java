package br.com.prove.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.prove.api.enumarations.StatusEnum;
import br.com.prove.api.model.Processo;
import br.com.prove.api.model.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDTO {
		
	private Long id;	
	private String nome;
	private String descricao;
	private Long posicao;
	private StatusEnum status;
	
	@JsonIgnoreProperties("atividades")
	private Processo processo;
	
	@JsonIgnoreProperties("atividade")
	private List<Tarefa> tarefas;
		
}
