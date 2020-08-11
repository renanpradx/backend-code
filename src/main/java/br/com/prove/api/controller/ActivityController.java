package br.com.prove.api.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.prove.api.dto.ActivityDTO;
import br.com.prove.api.dto.ProcessDTO;
import br.com.prove.api.exceptionhandler.Problem;
import br.com.prove.api.model.Atividade;
import br.com.prove.api.request.ActivityRequest;
import br.com.prove.search.controller.ActivitySearchCriteria;
import br.com.prove.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1")
@Api(tags = "Controller de Atividades")
public class ActivityController {

    @Autowired
    private ActivityService service;

    @GetMapping("/atividades")
    @ApiOperation(value = "Buscar todas os Atividades", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Buscar todas as Atividades", response = ActivityDTO.class),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParams({
    	@ApiImplicitParam (name = "size", value = "Tamanho da pagina", required = false, dataType = "int", paramType = "query", example = "1"),
    	@ApiImplicitParam (name = "page", value = "Pagina a ser exibida", required = false, dataType = "int", paramType = "query", example = "0"),
    	@ApiImplicitParam (name = "sort", value = "ordem da pagina", required = false, dataType = "String", paramType = "propertie", example = "nome")})
    public Page<ActivityDTO> findAll(ActivitySearchCriteria search, @PageableDefault(size = 10) Pageable pageable) {

        return service.findAll(search, pageable);

    }

    @GetMapping("/atividades/{id}")
    @ApiOperation(value = "Buscar Atividade pelo ID", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Buscar Atividade pelo ID", response = ActivityDTO.class),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "id", value = "ID a ser buscado", required = true, dataType = "int", paramType = "path", example = "1")
    public ResponseEntity<ActivityDTO> findById(@PathVariable @NotNull Long id) {

        ActivityDTO dto = service.findById(id);

        return (dto != null) ? new ResponseEntity<>(dto, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/atividades")
    @ApiOperation(value = "Criar Atividade", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Atividade criada com sucesso", response = ActivityDTO.class),
            @ApiResponse(code = 204, message = "Nenhuma Atividade encontrada"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    public ResponseEntity<ActivityDTO> create(@ApiParam(name="body", value="Representação de uma Atividade") @RequestBody @Valid ActivityRequest request, UriComponentsBuilder uri) {

        ActivityDTO dto = service.create(request);

        return ResponseEntity.created(
                uri.path("/atividades/{id}").buildAndExpand(dto.getId()).toUri()).body(dto);
    }

    @PutMapping("/atividades/{id}")
    @ApiOperation(value = "Atualizar Atividade pelo ID", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Atividade atualizada com sucesso.", response = Atividade.class),
            @ApiResponse(code = 204, message = "Nenhuma Atividade encontrada"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "id", value = "Id a ser atualizado", required = true, dataType = "int", paramType = "path", example = "1")
    public void send(@PathVariable Long id, @ApiParam(name="body", value="Representação de uma Atividade") @RequestBody @Valid ActivityRequest request) {

        service.update(id, request);

    }

    @DeleteMapping("/atividades/{id}")
    @ApiOperation(value = "Excluir Atividade pelo ID", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Atividade excluída com sucesso", response = Atividade.class),
            @ApiResponse(code = 204, message = "Nenhuma Atividade encontrado"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "id", value = "Id a ser excluído", required = true, dataType = "int", paramType = "path", example = "1")
    public void delete(@PathVariable Long id) {

        service.delete(id);

    }

    @GetMapping("/processos/{processoId}/atividades")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Buscar Atividades pelo ID do Processo", response = ProcessDTO.class),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "processoId", value = "Id do Processo a ser buscado.", required = true, dataType = "int", paramType = "path", example = "1")
    public List<ActivityDTO> findByProcess(@RequestHeader(required = true) String userId, @PathVariable Long processoId) {

        List<ActivityDTO> activities = service.findByProcess(processoId);

        return activities;
    }
}
