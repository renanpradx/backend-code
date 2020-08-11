package br.com.prove.api.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.prove.api.dto.TaskDTO;
import br.com.prove.api.exceptionhandler.Problem;
import br.com.prove.api.model.Tarefa;
import br.com.prove.api.request.TaskRequest;
import br.com.prove.search.controller.TaskSearchCriteria;
import br.com.prove.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1")
@Api(tags = "Controller de Tarefas")
public class TaskController {

    @Autowired
    private TaskService service;
    
    @GetMapping("/tarefas")
    @ApiOperation(value = "Buscar todas os Tarefas", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Buscar todas as Tarefas", response = TaskDTO.class),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParams({
    	@ApiImplicitParam (name = "size", value = "Tamanho da pagina", required = false, dataType = "int", paramType = "query", example = "1"),
    	@ApiImplicitParam (name = "page", value = "Pagina a ser exibida", required = false, dataType = "int", paramType = "query", example = "0"),
    	@ApiImplicitParam (name = "sort", value = "ordem da pagina", required = false, dataType = "String", paramType = "propertie", example = "nome")})
    public Page<TaskDTO> findAll(TaskSearchCriteria search,@PageableDefault(size = 10) Pageable pageable) {   	
    	
        return service.findAll(search, pageable);

    }

    @GetMapping("/tarefas/{id}")
    @ApiOperation(value = "Buscar Tarefa pelo ID", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Buscar Tarefa pelo ID", response = TaskDTO.class),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "id", value = "ID a ser buscado", required = true, dataType = "int", paramType = "path", example = "1")
    public ResponseEntity<TaskDTO> findById(@PathVariable @NotNull Long id) {

        TaskDTO dto = service.findById(id);

        return (dto != null) ? new ResponseEntity<>(dto, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/tarefas")
    @ApiOperation(value = "Criar Tarefa", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Tarefa criada com sucesso", response = TaskDTO.class),
            @ApiResponse(code = 204, message = "Nenhuma Tarefa encontrada"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    public ResponseEntity<TaskDTO> create(@ApiParam(name="body", value="Representação de uma Tarefa") @RequestBody @Valid TaskRequest request, UriComponentsBuilder uri) {
    	
        TaskDTO dto = service.create(request);
        return ResponseEntity.created(
                uri.path("/tarefas/{id}").buildAndExpand(dto.getId()).toUri()).body(dto);
    }

    @PutMapping("/tarefas/{id}")
    @ApiOperation(value = "Atualizar Tarefa pelo ID", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Tarefa atualizada com sucesso.", response = Tarefa.class),
            @ApiResponse(code = 204, message = "Nenhuma Tarefa encontrada"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "id", value = "Id a ser atualizado", required = true, dataType = "int", paramType = "path", example = "1")
    public void send(@PathVariable Long id, @ApiParam(name="body", value="Representação de uma Tarefa") @RequestBody @Valid TaskRequest request) {
       
       service.update(id, request);


    }

    @DeleteMapping("/tarefas/{id}")
    @ApiOperation(value = "Excluir Tarefa pelo ID", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Tarefa excluída com sucesso", response = Tarefa.class),
            @ApiResponse(code = 204, message = "Nenhuma Tarefa encontrado"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "id", value = "Id a ser excluído", required = true, dataType = "int", paramType = "path", example = "1")
    public void delete(@PathVariable Long id) {
    	
        service.delete(id);

    }
}
