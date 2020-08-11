package br.com.prove.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.prove.api.model.Office;
import br.com.prove.search.controller.OfficeSearchCriteria;

public interface OfficeFilterRepository {

    Page<Office> find(OfficeSearchCriteria search, Pageable pageable);
}
