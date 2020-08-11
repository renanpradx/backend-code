package br.com.prove.api.request;

import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.prove.api.enumarations.ClassificationEnum;
import br.com.prove.api.enumarations.StatusEnum;
import br.com.prove.api.model.Office;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MacroprocessRequest {

	@NotBlank
    private String nome;

	@NotBlank
    private String resumo;

	@NotNull
	@Enumerated(EnumType.STRING)
    private ClassificationEnum classificacao;
    
    @NotNull
    private Set<Office> cargos;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
