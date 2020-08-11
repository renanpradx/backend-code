package br.com.prove.api.mapper;

import br.com.prove.api.dto.ActivityDTO;
import br.com.prove.api.model.Atividade;
import br.com.prove.api.request.ActivityRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ActivityDTO modelToDto(Atividade activity) {
        return modelMapper.map(activity, ActivityDTO.class);
    }

    public Atividade dtoRequestToModel(ActivityRequest request) {
        return modelMapper.map(request, Atividade.class);
    }
}
