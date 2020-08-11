package br.com.prove.repository;

import br.com.prove.api.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OfficeRepository
        extends JpaRepository<Office, Long>, OfficeFilterRepository {
	
}