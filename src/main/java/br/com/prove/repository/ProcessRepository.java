package br.com.prove.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.prove.api.model.Processo;


@Repository
public interface ProcessRepository
		extends JpaRepository<Processo, Long>, ProcessFilterRepository {

	@Query("from Processo where macroprocesso.id = :macroprocessId")
	List<Processo> findAll(Long macroprocessId);

}
