package br.com.prove.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prove.api.dto.TaskDTO;
import br.com.prove.api.exception.PosicaoEmTarefaExistenteException;
import br.com.prove.api.exception.TarefaNaoEncontradaException;
import br.com.prove.api.mapper.TaskMapper;
import br.com.prove.api.model.MediaFile;
import br.com.prove.api.model.Tarefa;
import br.com.prove.api.request.TaskRequest;
import br.com.prove.repository.MediaFileRepository;
import br.com.prove.repository.TaskRepository;
import br.com.prove.search.controller.TaskSearchCriteria;

@Service
public class TaskService {

	@Autowired
	private TaskRepository repository;
	
	@Autowired
	AzureStorageService storage;
	
	@Autowired
	MediaFileRepository fileRepository;

	@Autowired
	private TaskMapper mapper;

	public Page<TaskDTO> findAll(TaskSearchCriteria search, Pageable pegeable) {

		Page<Tarefa> Tasks = repository.find(search, pegeable);

		return Tasks.map(Task -> mapper.modelToDto(Task));
	}

	public TaskDTO findById(Long id) {
		Tarefa task = repository.findById(id)
				.orElseThrow(() -> new TarefaNaoEncontradaException(id));

		return mapper.modelToDto(task);
	}

	@Transactional
	public void update(Long id, TaskRequest request) {
		
		Tarefa tarefa = repository.findByPosicao(request.getAtividade().getId(), request.getPosicao(), id);

		if(tarefa !=null ) {
			throw new PosicaoEmTarefaExistenteException(
					String.format("Atividade de código %d já possui uma tarefa na posição %d. "
							+ "Por favor, a inclua em outra posição.", request.getAtividade().getId(), request.getPosicao()));
		}

		
		Tarefa Task = mapper.dtoRequestToModel(request);
		Task.setId(id);

		repository.save(Task);
	}

	@Transactional
	public TaskDTO create(TaskRequest request) {
		
		Tarefa tarefa = repository.findByPosicao(request.getAtividade().getId(), request.getPosicao());

		if(tarefa !=null ) {
			throw new PosicaoEmTarefaExistenteException(
					String.format("Atividade de código %d já possui uma tarefa na posição %d. "
							+ "Por favor, a inclua em outra posição.", request.getAtividade().getId(), request.getPosicao()));
		}

		Tarefa Task = mapper.dtoRequestToModel(request);

		return mapper.modelToDto(repository.save(Task));
	}

	@Transactional
	public void delete(Long id) {
		
		Optional<Tarefa> currentTask = repository.findById(id);
		
		repository.deleteById(id);
		
		for (int i = 0; i < currentTask.get().getArquivo().size(); i++) {
			
			MediaFile currentFile = currentTask.get().getArquivo().get(i);
			storage.deleteFile(currentFile.getName());
			fileRepository.delete(currentFile);			
		}
		
	}

}
