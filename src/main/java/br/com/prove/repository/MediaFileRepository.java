package br.com.prove.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prove.api.model.MediaFile;
@Repository
public interface MediaFileRepository extends JpaRepository<MediaFile, Long>{

}
