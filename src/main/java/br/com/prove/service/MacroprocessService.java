package br.com.prove.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prove.api.dto.MacroprocessDTO;
import br.com.prove.api.exception.EntidadeEmUsoException;
import br.com.prove.api.exception.MacroprocessoNaoEncontradoException;
import br.com.prove.api.mapper.MacroprocessMapper;
import br.com.prove.api.model.Macroprocesso;
import br.com.prove.api.request.MacroprocessRequest;
import br.com.prove.repository.MacroprocessRepository;
import br.com.prove.search.controller.MacroprocessSearchCriteria;

@Service
public class MacroprocessService {

	@Autowired
	private MacroprocessRepository repository;

	@Autowired
	private MacroprocessMapper mapper;

	public Page<MacroprocessDTO> findAll(MacroprocessSearchCriteria search, Pageable pageable) {

		Page<Macroprocesso> macroprocesses = repository.find(search, pageable);

		return macroprocesses.map(macroprocess -> mapper.modelToDto(macroprocess));
	}

	public MacroprocessDTO findById(Long id) {
		Macroprocesso macroproprocess = repository.findById(id)
				.orElseThrow(() -> new MacroprocessoNaoEncontradoException(id));

		return mapper.modelToDto(macroproprocess);
	}

	@Transactional
	public void update(Long id, MacroprocessRequest request) {
		Macroprocesso macroprocess = mapper.dtoRequestToModel(request);
		macroprocess.setId(id);

		repository.save(macroprocess);
	}

	@Transactional
	public MacroprocessDTO create(MacroprocessRequest request) {

		Macroprocesso macroprocess = mapper.dtoRequestToModel(request);

		return mapper.modelToDto(repository.save(macroprocess));
	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
			repository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new MacroprocessoNaoEncontradoException(id);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Macroprocesso de código %d não pode ser removido, pois tem processos associados", id));
		}
	}
	
}
