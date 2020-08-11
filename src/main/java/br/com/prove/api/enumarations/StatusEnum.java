package br.com.prove.api.enumarations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
	
	ACTIVE(0L, "Active"),
	INACTIVE(1L, "Inactive");
	
	Long id;
	String descricao;

}
