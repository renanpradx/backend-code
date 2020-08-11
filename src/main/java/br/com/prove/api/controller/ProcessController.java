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

import br.com.prove.api.dto.ProcessDTO;
import br.com.prove.api.exceptionhandler.Problem;
import br.com.prove.api.model.Processo;
import br.com.prove.api.request.ProcessRequest;
import br.com.prove.search.controller.ProcessSearchCriteria;
import br.com.prove.service.ProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1")
@Api(tags = "Controller de Processos")
public class ProcessController {

    @Autowired
    private ProcessService service;

    @GetMapping("/processos")
    @ApiOperation(value = "Buscar todos os Processos", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Buscar todos os Processos", response = ProcessDTO.class),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParams({
    	@ApiImplicitParam (name = "size", value = "Tamanho da pagina", required = false, dataType = "int", paramType = "query", example = "1"),
    	@ApiImplicitParam (name = "page", value = "Pagina a ser exibida", required = false, dataType = "int", paramType = "query", example = "0"),
    	@ApiImplicitParam (name = "sort", value = "ordem da pagina", required = false, dataType = "String", paramType = "propertie", example = "nome")})
    public Page<ProcessDTO> findAll(ProcessSearchCriteria search, @PageableDefault(size = 10) Pageable pageable) {

        return service.findAll(search, pageable);

    }

    @GetMapping("/processos/{id}")
    @ApiOperation(value = "Buscar Processo pelo ID", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Buscar Processo pelo ID", response = ProcessDTO.class),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "id", value = "ID a ser buscado", required = true, dataType = "int", paramType = "path", example = "1")
    public ResponseEntity<ProcessDTO> findById(@PathVariable @NotNull Long id) {

        ProcessDTO dto = service.findById(id);

        return (dto != null) ? new ResponseEntity<>(dto, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/processos")
    @ApiOperation(value = "Criar Processo", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Processo criado com sucesso", response = ProcessDTO.class),
            @ApiResponse(code = 204, message = "Nenhum Processo encontrado"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    public ResponseEntity<ProcessDTO> create(@ApiParam(name="body", value="Representação de um Processo") @RequestBody @Valid ProcessRequest request, UriComponentsBuilder uri) {

        ProcessDTO dto = service.create(request);

        return ResponseEntity.created(
                uri.path("/processos/{id}").buildAndExpand(dto.getId()).toUri()).body(dto);
    }

    @PutMapping("/processos/{id}")
    @ApiOperation(value = "Atualizar Processo pelo ID", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Processo atualizado com sucesso.", response = Processo.class),
            @ApiResponse(code = 204, message = "Nenhum Processo encontrado"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "id", value = "Id a ser atualizado", required = true, dataType = "int", paramType = "path", example = "1")
    public void send(@PathVariable Long id, @ApiParam(name="body", value="Representação de um Processo") @RequestBody @Valid ProcessRequest request) {

        service.update(id, request);

    }

    @DeleteMapping("/processos/{id}")
    @ApiOperation(value = "Excluir Processo pelo ID", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Processo excluído com sucesso", response = Processo.class),
            @ApiResponse(code = 204, message = "Nenhum Processo encontrado"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "id", value = "Id a ser excluído", required = true, dataType = "int", paramType = "path", example = "1")
    public void delete(@PathVariable Long id) {

        service.delete(id);

    }

    @GetMapping("/macroprocessos/{macroprocessoId}/processos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Buscar todos os Processos por Macroprocesso", response = ProcessDTO.class),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "macroprocessoId", value = "Id do Macroprocesso a ser buscado", required = true, dataType = "int", paramType = "path", example = "1")
    public List<ProcessDTO> findByMacroprocess(@RequestHeader(required = true) String userId, @PathVariable Long macroprocessoId) {

        List<ProcessDTO> processes = service.findByMacroprocess(macroprocessoId);

        return processes;
    }
}
