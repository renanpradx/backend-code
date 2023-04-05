package br.com.prove.repository;

import br.com.prove.api.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository
        extends JpaRepository<Marca, Long> {
}
