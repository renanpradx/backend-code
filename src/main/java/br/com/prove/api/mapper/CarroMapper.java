package br.com.prove.api.mapper;

import br.com.prove.api.dto.CarroMarcaDTO;
import br.com.prove.api.model.Carro;
import br.com.prove.api.request.CarroRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarroMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CarroMarcaDTO modelToDto(Carro carro) {
        return modelMapper.map(carro, CarroMarcaDTO.class);
    }

    public Carro dtoRequestToModel(CarroRequest request) {
        return modelMapper.map(request, Carro.class);
    }
}
