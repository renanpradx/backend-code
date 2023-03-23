package br.com.prove.api.dto;

import br.com.prove.api.model.Situacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocioDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private Situacao situacao;
}
