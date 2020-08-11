package br.com.prove.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.prove.api.model.Macroprocesso;
import br.com.prove.search.controller.MacroprocessSearchCriteria;

public interface MacroprocessFilterRepository {

	Page<Macroprocesso> find(MacroprocessSearchCriteria search, Pageable pageable);
}
