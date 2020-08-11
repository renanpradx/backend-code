package br.com.prove.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.prove.api.model.Processo;
import br.com.prove.search.controller.ProcessSearchCriteria;

public interface ProcessFilterRepository {

    Page<Processo> find(ProcessSearchCriteria search, Pageable pageable);
}
