package br.com.prove.repository.criteria;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.prove.api.model.Processo;
import br.com.prove.repository.ProcessFilterRepository;
import br.com.prove.search.controller.ProcessSearchCriteria;

@Repository
public class ProcessFilterRepositoryImpl implements ProcessFilterRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Processo> find(ProcessSearchCriteria search, Pageable pageable) {

    	StringBuilder jpql = new StringBuilder();
		jpql.append("from Processo p ");
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		if (search.getCargo() != null) {
			jpql.append("join fetch p.cargos c ");
			jpql.append("where c.descricao like :cargo ");
			parametros.put("cargo", "%"+search.getCargo()+"%");
		}else {
			jpql.append("where 0 = 0 ");
		}
		
		if (search.getMacroprocesso() != null) {
			jpql.append("and p.macroprocesso.nome like :macroprocesso ");
			parametros.put("macroprocesso", "%"+search.getMacroprocesso()+"%");
		}
		
		if (search.getStatus() != null) {
			jpql.append("and p.status like :status ");
			parametros.put("status", search.getStatus());
		}
		
		TypedQuery<Processo> query = manager
				.createQuery(jpql.toString(), Processo.class);
		
		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
		
		Long size = Long.valueOf(query.getResultList().size());
		
		query.setFirstResult((int) pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		Page<Processo> page = new PageImpl<>(query.getResultList(), pageable, size);
		return page;
    }

}
