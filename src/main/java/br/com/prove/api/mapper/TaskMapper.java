package br.com.prove.api.mapper;

import br.com.prove.api.dto.TaskDTO;
import br.com.prove.api.model.Tarefa;
import br.com.prove.api.request.TaskRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    @Autowired
    private ModelMapper modelMapper;

    public TaskDTO modelToDto(Tarefa task) {
        return modelMapper.map(task, TaskDTO.class);
    }

    public Tarefa dtoRequestToModel(TaskRequest request) {
        return modelMapper.map(request, Tarefa.class);
    }
    
    public TaskRequest ModeltoRquestDto(Tarefa task) {
        return modelMapper.map(task, TaskRequest.class);
    }
}
