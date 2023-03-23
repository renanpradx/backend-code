package br.com.prove.api.request;

import br.com.prove.api.model.Marca;
import br.com.prove.api.model.Socio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarroRequest {

    @NotNull
    private Long id;

    @NotBlank
    private String modelo;

    @NotBlank
    private String cor;

    @NotBlank
    private String placa;

    @NotNull
    private Socio socio;

    @NotNull
    private Marca marca;
}
