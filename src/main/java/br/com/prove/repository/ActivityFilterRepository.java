package br.com.prove.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.prove.api.model.Atividade;
import br.com.prove.search.controller.ActivitySearchCriteria;

public interface ActivityFilterRepository {

	Page<Atividade> find(ActivitySearchCriteria search, Pageable pageable);
}
