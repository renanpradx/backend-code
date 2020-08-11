package br.com.prove.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prove.api.dto.OfficeDTO;
import br.com.prove.api.exception.CargoNaoEncontradoException;
import br.com.prove.api.mapper.OfficeMapper;
import br.com.prove.api.model.Office;
import br.com.prove.api.request.OfficeRequest;
import br.com.prove.repository.OfficeRepository;
import br.com.prove.search.controller.OfficeSearchCriteria;

@Service
public class OfficeService {

	@Autowired
	private OfficeRepository repository;

	@Autowired
	private OfficeMapper mapper;

	public List<OfficeDTO> findAll() {

		List<Office> offices = repository.findAll();

		return offices.stream()
                .map(office -> mapper.modelToDto(office))
                .collect(Collectors.toList());
	}

	public OfficeDTO findById(Long id) {
		Office office = repository.findById(id)
				.orElseThrow(() -> new CargoNaoEncontradoException(id));

		return mapper.modelToDto(office);
	}

}
