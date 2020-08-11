package br.com.prove.api.mapper;

import br.com.prove.api.dto.OfficeDTO;
import br.com.prove.api.model.Office;
import br.com.prove.api.request.OfficeRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfficeMapper {

    @Autowired
    private ModelMapper modelMapper;

    public OfficeDTO modelToDto(Office office) {
        return modelMapper.map(office, OfficeDTO.class);
    }

    public Office dtoRequestToModel(OfficeRequest request) {
        return modelMapper.map(request, Office.class);
    }
}
