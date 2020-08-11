package br.com.prove.repository.criteria;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.prove.api.model.Atividade;
import br.com.prove.repository.ActivityFilterRepository;
import br.com.prove.search.controller.ActivitySearchCriteria;

@Repository
public class ActivityFilterRepositoryImpl implements ActivityFilterRepository{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Atividade> find(ActivitySearchCriteria search, Pageable pageable) {
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("from Atividade a where 0 = 0 ");
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		if (search.getStatus() != null) {
			jpql.append("and a.status like :status ");
			parametros.put("status", search.getStatus());
		}
			
		TypedQuery<Atividade> query = manager
				.createQuery(jpql.toString(), Atividade.class);
		
		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
		Long size = Long.valueOf(query.getResultList().size());
		
		query.setFirstResult((int) pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		Page<Atividade> page = new PageImpl<>(query.getResultList(), pageable, size);
		return page;
		
	}
	
	
}
