package br.com.prove.api.dto;

import br.com.prove.api.model.Marca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarroMarcaDTO {

    private Long id;
    private String modelo;
    private String cor;
    private String placa;
    private Marca marca;
}
