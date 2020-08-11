package br.com.prove.api.enumarations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClassificationEnum {

    RESULTADOS("RESULTADOS","Resultados"),
    GENTE("GENTE","Gente"),
    CLIENTE("CLIENTE", "Cliente"),
    NEGÓCIOS("NEGÓCIOS", "Negócios/Comercial"),
    OPERACIONAL("OPERACIONAL","Operacional");

	private String nome;
    private String descricao;
	
}
