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

import br.com.prove.api.dto.ActivityDTO;
import br.com.prove.api.exception.AtividadeNaoEncontradaException;
import br.com.prove.api.exception.EntidadeEmUsoException;
import br.com.prove.api.exception.PosicaoEmAtividadeExistenteException;
import br.com.prove.api.mapper.ActivityMapper;
import br.com.prove.api.model.Atividade;
import br.com.prove.api.request.ActivityRequest;
import br.com.prove.repository.ActivityRepository;
import br.com.prove.search.controller.ActivitySearchCriteria;

@Service
public class ActivityService {

	@Autowired
	private ActivityRepository repository;

	@Autowired
	private ActivityMapper mapper;

	public Page<ActivityDTO> findAll(ActivitySearchCriteria search, Pageable pageable) {

		Page<Atividade> activities = repository.find(search, pageable);

		return activities.map(Activity -> mapper.modelToDto(Activity));
	}

	public ActivityDTO findById(Long id) {
		Atividade activity = repository.findById(id)
				.orElseThrow(() -> new AtividadeNaoEncontradaException(id));

		return mapper.modelToDto(activity);
	}

	@Transactional
	public void update(Long id, ActivityRequest request) {

		Atividade atividade = repository.findByPosicao(request.getProcesso().getId(), request.getPosicao(), id);

		if(atividade !=null ) {
			throw new PosicaoEmAtividadeExistenteException(
					String.format("Processo de código %d já possui uma atividade na posição %d. "
							+ "Por favor, a inclua em outra posição.", request.getProcesso().getId(), request.getPosicao()));
		}
		
		Atividade activity = mapper.dtoRequestToModel(request);
		activity.setId(id);

		repository.save(activity);
	}

	@Transactional
	public ActivityDTO create(ActivityRequest request) {

		Atividade atividade = repository.findByPosicao(request.getProcesso().getId(), request.getPosicao());
		
		if(atividade !=null ) {
			throw new PosicaoEmAtividadeExistenteException(
					String.format("Processo de código %d já possui uma atividade na posição %d. "
							+ "Por favor, a inclua em outra posição.", request.getProcesso().getId(), request.getPosicao()));
		}
		
		Atividade activity = mapper.dtoRequestToModel(request);

		return mapper.modelToDto(repository.save(activity));
	}

	public List<ActivityDTO> findByProcess(Long processId) {
		List<Atividade> activities = repository.findByProcessId(processId);

		return activities.stream()
				.map(activity -> mapper.modelToDto(activity))
				.collect(Collectors.toList());
	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
			repository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new AtividadeNaoEncontradaException(id);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Atividade de código %d não pode ser removido, pois tem tarefas associadas", id));
		}
	}

}
