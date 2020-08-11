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

import br.com.prove.api.dto.MacroprocessDTO;
import br.com.prove.api.exceptionhandler.Problem;
import br.com.prove.api.model.Macroprocesso;
import br.com.prove.api.request.MacroprocessRequest;
import br.com.prove.search.controller.MacroprocessSearchCriteria;
import br.com.prove.service.MacroprocessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1")
@Api(tags = "Controller de Macroprocessos")
public class MacroprocessController {

    @Autowired
    private MacroprocessService service;

    @GetMapping("/macroprocessos")
    @ApiOperation(value = "Buscar todos os Macroprocessos", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Buscar todos os macroprocessos", response = MacroprocessDTO.class),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParams({
    	@ApiImplicitParam (name = "size", value = "Tamanho da pagina", required = false, dataType = "int", paramType = "query", example = "1"),
    	@ApiImplicitParam (name = "page", value = "Pagina a ser exibida", required = false, dataType = "int", paramType = "query", example = "0"),
    	@ApiImplicitParam (name = "sort", value = "ordem da pagina", required = false, dataType = "String", paramType = "propertie", example = "nome")})
    public Page<MacroprocessDTO> findAll(MacroprocessSearchCriteria search, @PageableDefault(size = 10) Pageable pageable) {

        return service.findAll(search, pageable);

    }

    @GetMapping("/macroprocessos/{id}")
    @ApiOperation(value = "Buscar Macroprocesso pelo ID", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Buscar Macroprocesso pelo ID", response = MacroprocessDTO.class),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "id", value = "ID a ser buscado", required = true, dataType = "int", paramType = "path", example = "1")
    public ResponseEntity<MacroprocessDTO> findById(@PathVariable @NotNull Long id) {

        MacroprocessDTO dto = service.findById(id);

        return (dto != null) ? new ResponseEntity<>(dto, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/macroprocessos")
    @ApiOperation(value = "Criar Macroprocesso", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Macroprocesso criado com sucesso", response = MacroprocessDTO.class),
            @ApiResponse(code = 204, message = "Nenhum macroprocesso encontrado"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    public ResponseEntity<MacroprocessDTO> create(@ApiParam(name="body", value="Representação de um Macroprocesso") @RequestBody @Valid MacroprocessRequest request, UriComponentsBuilder uri) {

        MacroprocessDTO dto = service.create(request);

        return ResponseEntity.created(
                uri.path("/macroprocessos/{id}").buildAndExpand(dto.getId()).toUri()).body(dto);
    }

    @PutMapping("/macroprocessos/{id}")
    @ApiOperation(value = "Atualizar Macroprocesso pelo ID", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Macroprocesso atualizado com sucesso.", response = Macroprocesso.class),
            @ApiResponse(code = 204, message = "Nenhum macroprocesso encontrado"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "id", value = "Id a ser atualizado", required = true, dataType = "int", paramType = "path", example = "1")
    public void send(@PathVariable Long id, @ApiParam(name="body", value="Representação de um Macroprocesso") @RequestBody @Valid MacroprocessRequest request) {

        service.update(id, request);

    }

    @DeleteMapping("/macroprocessos/{id}")
    @ApiOperation(value = "Excluir Macroprocesso pelo ID", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Macroprocesso excluído com sucesso", response = Macroprocesso.class),
            @ApiResponse(code = 204, message = "Nenhum macroprocesso encontrado"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "id", value = "Id a ser excluído", required = true, dataType = "int", paramType = "path", example = "1")
    public void delete(@PathVariable Long id) {

        service.delete(id);

    }
}
