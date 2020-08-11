package br.com.prove.repository.criteria;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.prove.api.model.Macroprocesso;
import br.com.prove.repository.MacroprocessFilterRepository;
import br.com.prove.search.controller.MacroprocessSearchCriteria;

@Repository
public class MacroprocessFilterRepositoryImpl implements MacroprocessFilterRepository{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Macroprocesso> find(MacroprocessSearchCriteria search, Pageable pageable) {
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("from Macroprocesso m ");
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		if (search.getCargo() != null) {
			jpql.append("join fetch m.cargos c ");
			jpql.append("where c.descricao like :cargo ");
			parametros.put("cargo", "%"+search.getCargo()+"%");
		}else {
			jpql.append("where 0 = 0 ");
		}
		
		if (search.getClassificacao() != null) {
			jpql.append("and m.classificacao like :classificacao ");
			parametros.put("classificacao", search.getClassificacao());
		}
		
		if (search.getStatus() != null) {
			jpql.append("and m.status like :status ");
			parametros.put("status", search.getStatus());
		}
		
		jpql.append("order by m.classificacao ASC");
		
		
		TypedQuery<Macroprocesso> query = manager
				.createQuery(jpql.toString(), Macroprocesso.class);
					
		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
				
		Long size = Long.valueOf(query.getResultList().size());
		
		query.setFirstResult((int) pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		Page<Macroprocesso> page = new PageImpl<>(query.getResultList(), pageable, size);
		return page;
		
	}
	
	
}
