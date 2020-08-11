package br.com.prove.repository.criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.prove.api.model.Office;
import br.com.prove.repository.OfficeFilterRepository;
import br.com.prove.search.controller.OfficeSearchCriteria;

@Repository
public class OfficeFilterRepositoryImpl implements OfficeFilterRepository{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Office> find(OfficeSearchCriteria search, Pageable pageable) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);
        Root<Office> root = criteria.from(Office.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (search.getStatus() != null) {
            predicates.add(builder.equal(root.get("status"), search.getStatus()));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Office> query = manager.createQuery(criteria);
        
        query.setFirstResult((int) pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		Page<Office> page = new PageImpl<>(query.getResultList(), pageable, query.getResultList().size());
        return page;
    }


}
