package br.com.prove.search.controller;

import br.com.prove.api.enumarations.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Informações básicas sobre Tarefa", value = "Tarefa")
public class TaskSearchCriteria {
	
	@ApiModelProperty(value = "O status da Tarefa a ser buscada", required = false, position = 4, dataType = "string", example = "ACTIVE")
	private StatusEnum status;
	
}
