package br.com.prove.repository;
import br.com.prove.api.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocioRepository
        extends JpaRepository<Socio, Long> {
}
