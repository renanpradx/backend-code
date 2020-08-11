package br.com.prove.api.dto;

import java.util.Set;

import br.com.prove.api.enumarations.ClassificationEnum;
import br.com.prove.api.enumarations.StatusEnum;
import br.com.prove.api.model.Office;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MacroprocessDTO {

    private Long id;
    private String nome;
    private String resumo;
    private ClassificationEnum classificacao;
    private Set<Office> cargos;
    StatusEnum status;
       
}
