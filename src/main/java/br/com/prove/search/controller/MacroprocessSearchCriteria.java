package br.com.prove.search.controller;

import br.com.prove.api.enumarations.ClassificationEnum;
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
@ApiModel(description = "Informações básicas sobre Macroprocesso", value = "Macroprocesso")
public class MacroprocessSearchCriteria {

	@ApiModelProperty(value = "O status do Macroprocesso a ser buscado", required = false, position = 1, dataType = "Enum", example = "ACTIVE")
	private StatusEnum status;

	@ApiModelProperty(value = "A classificação do Macroprocesso a ser buscada", required = false, position = 2, dataType = "Enum", example = "RESULTADOS")
	private ClassificationEnum classificacao;

	@ApiModelProperty(value = "Os cargos do Macroprocesso autorizados", required = false, position = 3, dataType = "String", example = "Vendedor")
	private String cargo;
}
