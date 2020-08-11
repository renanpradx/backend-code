package br.com.prove.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.prove.api.model.Atividade;


@Repository
public interface ActivityRepository 
	extends JpaRepository<Atividade, Long>, ActivityFilterRepository {

	@Query("from Atividade where processo.id = :processId order by posicao ASC")
	List<Atividade> findByProcessId(Long processId);

	@Query("from Atividade where processo.id = :processoId and posicao = :posicao")
	Atividade findByPosicao(Long processoId, Long posicao);

	@Query("from Atividade where processo.id = :processoId and posicao = :posicao and id != :id")
	Atividade findByPosicao(Long processoId, Long posicao, Long id);

		
	
}