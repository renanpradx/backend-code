package br.com.prove.api.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prove.api.dto.OfficeDTO;
import br.com.prove.api.exceptionhandler.Problem;
import br.com.prove.service.OfficeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1")
@Api(tags = "Controller de Cargos")
public class OfficeController {

    @Autowired
    private OfficeService service;

    @GetMapping("/cargos")
    @ApiOperation(value = "Buscar todos os Cargos", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Buscar todos os Cargos", response = OfficeDTO.class),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
   public List<OfficeDTO> findAll() {

        return service.findAll();

    }

    @GetMapping("/cargos/{id}")
    @ApiOperation(value = "Buscar Cargo pelo ID", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Buscar Cargo pelo ID", response = OfficeDTO.class),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "id", value = "ID a ser buscado", required = true, dataType = "int", paramType = "path", example = "1")
    public ResponseEntity<OfficeDTO> findById(@PathVariable @NotNull Long id) {

        OfficeDTO dto = service.findById(id);

        return (dto != null) ? new ResponseEntity<>(dto, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
