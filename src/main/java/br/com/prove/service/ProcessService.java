package br.com.prove.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prove.api.dto.ProcessDTO;
import br.com.prove.api.exception.EntidadeEmUsoException;
import br.com.prove.api.exception.ProcessoNaoEncontradoException;
import br.com.prove.api.mapper.ProcessMapper;
import br.com.prove.api.model.Processo;
import br.com.prove.api.request.ProcessRequest;
import br.com.prove.repository.ProcessRepository;
import br.com.prove.search.controller.ProcessSearchCriteria;

@Service
public class ProcessService {

    @Autowired
    private ProcessRepository repository;
    
    @Autowired
    private ProcessMapper mapper;

    public Page<ProcessDTO> findAll(ProcessSearchCriteria search, Pageable pageable) {

		Page<Processo> processes = repository.find(search, pageable);

		return processes.map(process -> mapper.modelToDto(process));
    }

	public ProcessDTO findById(Long id) {
		Processo processo = repository.findById(id)
				.orElseThrow(() -> new ProcessoNaoEncontradoException(id));

		return mapper.modelToDto(processo);
	}

    @Transactional
    public void update(Long id, ProcessRequest request) {
    	
    	Processo process = mapper.dtoRequestToModel(request);
    	process.setId(id);
    
    	repository.save(process);
    }

    @Transactional
    public ProcessDTO create(ProcessRequest request) {
    	
    	Processo process = mapper.dtoRequestToModel(request);
    	
        return mapper.modelToDto(repository.save(process));
    }

	public List<ProcessDTO> findByMacroprocess(Long macroprocessId) {
	List<Processo> processes = repository.findAll(macroprocessId);       
        
        return processes.stream()
        		.map(p -> mapper.modelToDto(p))
        		.collect(Collectors.toList());
    }

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
			repository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ProcessoNaoEncontradoException(id);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Processo de código %d não pode ser removido, pois tem ativdades associadas", id));
		}
	}
}
