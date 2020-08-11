package br.com.prove.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.prove.api.model.Tarefa;
import br.com.prove.search.controller.TaskSearchCriteria;

public interface TaskFilterRepository {

    Page<Tarefa> find(TaskSearchCriteria search, Pageable pageable);
}
