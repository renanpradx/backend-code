package br.com.prove.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.prove.api.model.Tarefa;


@Repository
public interface TaskRepository
        extends JpaRepository<Tarefa, Long>, TaskFilterRepository {

	@Query("from Tarefa where atividade.id = :id and posicao = :posicao")
	Tarefa findByPosicao(Long id, int posicao);

	@Query("from Tarefa where atividade.id = :idAtividade and posicao = :posicao and id != :id")
	Tarefa findByPosicao(Long idAtividade, int posicao, Long id);
	
}
