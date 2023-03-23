package br.com.prove.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SituacaoDTO {

    private Long id;
    private String situacao;
}
