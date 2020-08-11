package br.com.prove.api.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.prove.api.enumarations.StatusEnum;
import br.com.prove.api.model.Atividade;
import br.com.prove.api.model.MediaFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;
    
    @NotNull
    private List<MediaFile> arquivo;

    @NotNull
    private boolean inicio;

    @NotNull
    private int posicao;
    
    @NotNull
    private StatusEnum status;
    @NotNull
    private Atividade atividade;
	
}
