package br.com.prove.api.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.prove.api.enumarations.StatusEnum;
import br.com.prove.api.model.DescriptionProcess;
import br.com.prove.api.model.Macroprocesso;
import br.com.prove.api.model.Office;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessDTO {

	private Long id;
    private String nome;
    private Macroprocesso macroprocesso;
    private Set<Office> cargos;
    private Set<DescriptionProcess> descricoes;
    
    @JsonIgnoreProperties("processo")
    private List<ActivityDTO> atividades;
    
    StatusEnum status;
}
