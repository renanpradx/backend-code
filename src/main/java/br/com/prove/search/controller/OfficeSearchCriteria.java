package br.com.prove.search.controller;

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
@ApiModel(description = "Informações básicas sobre Cargo", value = "Cargo")
public class OfficeSearchCriteria {
	
	@ApiModelProperty(value = "O status do Cargo a ser buscado", required = false, position = 4, dataType = "string", example = "ACTIVE")
	private String status;
	
}
