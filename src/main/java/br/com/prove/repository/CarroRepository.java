package br.com.prove.repository;
import br.com.prove.api.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository
        extends JpaRepository<Carro, Long>, OfficeFilterRepository {

    @Query("from Carro where id = :Id")
    Carro findByCarroId(Long Id);
}
