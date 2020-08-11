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
@ApiModel(description = "Informações básicas sobre Processo", value = "Processo")
public class ProcessSearchCriteria {

	@ApiModelProperty(value = "O status do Processo a ser buscado", required = false, position = 1, dataType = "Enum", example = "ACTIVE")
	private StatusEnum status;

	@ApiModelProperty(value = "Os cargos do Processo autorizados", required = false, position = 2, dataType = "String", example = "Vendedor")
	private String cargo;

	@ApiModelProperty(value = "O Macroprocesso do Processo a ser buscado", required = false, position = 3, dataType = "String", example = "Gestão de Estoque")
	private String macroprocesso;
	
}
