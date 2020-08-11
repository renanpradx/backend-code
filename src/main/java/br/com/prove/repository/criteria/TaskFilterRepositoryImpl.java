package br.com.prove.repository.criteria;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.prove.api.model.Tarefa;
import br.com.prove.repository.TaskFilterRepository;
import br.com.prove.search.controller.TaskSearchCriteria;

@Repository
public class TaskFilterRepositoryImpl implements TaskFilterRepository{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Tarefa> find(TaskSearchCriteria search, Pageable pageable) {

    	StringBuilder jpql = new StringBuilder();
		jpql.append("from Tarefa t where 0 = 0 ");
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		if (search.getStatus() != null) {
			jpql.append("and t.status like :status ");
			parametros.put("status", search.getStatus());
		}
		
		TypedQuery<Tarefa> query = manager
				.createQuery(jpql.toString(), Tarefa.class);
			
		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
		
		Long size = Long.valueOf(query.getResultList().size());
		
		query.setFirstResult((int) pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		Page<Tarefa> page = new PageImpl<>(query.getResultList(), pageable, size);
		
		return page;
		
	}
	


}
