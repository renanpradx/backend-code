package br.com.prove.repository;
import br.com.prove.api.model.Situacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituacaoRepository
        extends JpaRepository<Situacao, Long> {

}
