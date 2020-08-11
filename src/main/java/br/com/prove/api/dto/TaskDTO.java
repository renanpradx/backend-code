package br.com.prove.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.prove.api.enumarations.StatusEnum;
import br.com.prove.api.model.MediaFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private Long id;
    private String nome;
    private String descricao;
    private List<MediaFile> arquivo;
    private boolean inicio;
    private int posicao;
    StatusEnum status;    
    
    @JsonIgnoreProperties({"processo","tarefas"})
    private ActivityDTO atividade;
}
