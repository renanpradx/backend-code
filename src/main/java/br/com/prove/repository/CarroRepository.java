package br.com.prove.repository;

import br.com.prove.api.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository
        extends JpaRepository<Carro, Long>, OfficeFilterRepository {
}
