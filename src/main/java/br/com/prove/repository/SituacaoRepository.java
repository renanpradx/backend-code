package br.com.prove.repository;
import br.com.prove.api.model.Situacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SituacaoRepository
        extends JpaRepository<Situacao, Long> {

    @Query("from Situacao where id = :Id")
    Situacao findBySituacaoId(Long Id);

}
