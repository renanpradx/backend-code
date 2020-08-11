package br.com.prove.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prove.api.model.Macroprocesso;


@Repository
public interface MacroprocessRepository
		extends JpaRepository<Macroprocesso, Long>, MacroprocessFilterRepository{

}
