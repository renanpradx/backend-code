package br.com.prove.api.request;

import br.com.prove.api.enumarations.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfficeRequest {

    @NotBlank
    private String descricao;

    @NotNull
    private StatusEnum status;
		
}
