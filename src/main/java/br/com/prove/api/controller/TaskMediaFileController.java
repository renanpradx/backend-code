package br.com.prove.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.prove.api.exceptionhandler.Problem;
import br.com.prove.api.model.MediaFile;
import br.com.prove.repository.MediaFileRepository;
import br.com.prove.service.MediaFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("v1")
@Api(tags = "Upload de Arquivos")
public class TaskMediaFileController {
	
	@Autowired
	private MediaFileService service;
	
	@Autowired
	private MediaFileRepository mediaFileRepository;
	
	@ApiOperation(value = "Adicionar arquivo ", httpMethod = "POST")
	   @ApiResponses({
	           @ApiResponse(code = 201, message = "Salvar arquivo", response = MediaFile.class)})
	@PostMapping("/arquivos")
	public ResponseEntity<MediaFile> create(@Valid @RequestParam MultipartFile file){
		MediaFile savedfile = service.create(file);
		return ResponseEntity.created(savedfile.getUrl()).body(savedfile);
	}
	@ApiOperation(value = "Listar arquivos", httpMethod = "GET")
	   @ApiResponses({
	           @ApiResponse(code = 200, message = "Buscar todas as Tarefas", response = MediaFile.class),
	           @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@GetMapping("/arquivos")
	
	public List<MediaFile> list(){
		return mediaFileRepository.findAll();
	}
	@ApiOperation(value = "Atualizar Arquivo pelo ID", httpMethod = "PUT")
	   @ApiResponses({
	           @ApiResponse(code = 200, message = "Arquivo atualizado com sucesso.", response = MediaFile.class),
	           @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	   @ApiImplicitParam(name = "id", value = "Id a ser atualizado", required = true, dataType = "int", paramType = "path", example = "1")
	@PutMapping("/tarefas/id/arquivos/{id}")
	public ResponseEntity<MediaFile> update(@Valid @RequestParam MultipartFile file, @PathVariable long id){
		MediaFile savedFile = service.update(file, id);
		return ResponseEntity.ok(savedFile);
	}
}
