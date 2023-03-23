package br.com.prove.api.dto;

import br.com.prove.api.model.Marca;
import br.com.prove.api.model.Socio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarroDTO {

    private Long id;
    private String modelo;
    private String cor;
    private String placa;
    private Socio socio;
    private Marca marca;
}
