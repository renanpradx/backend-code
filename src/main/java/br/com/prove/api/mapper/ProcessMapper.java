package br.com.prove.api.mapper;

import br.com.prove.api.dto.ProcessDTO;
import br.com.prove.api.model.Processo;
import br.com.prove.api.request.ProcessRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProcessDTO modelToDto(Processo process) {
        return modelMapper.map(process, ProcessDTO.class);
    }

    public Processo dtoRequestToModel(ProcessRequest request) {
        return modelMapper.map(request, Processo.class);
    }
}
