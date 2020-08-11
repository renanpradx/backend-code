package br.com.prove.api.request;

import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.prove.api.enumarations.StatusEnum;
import br.com.prove.api.model.DescriptionProcess;
import br.com.prove.api.model.Macroprocesso;
import br.com.prove.api.model.Office;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessRequest {

    @NotBlank
    private String nome;

    @NotNull
    private Macroprocesso macroprocesso;

    @NotNull
    private Set<DescriptionProcess> descricoes;
        
    @NotNull
    private Set<Office> cargos;

    @NotNull
    @Enumerated(EnumType.STRING)
    StatusEnum status;

}
