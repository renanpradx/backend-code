package br.com.prove.api.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.prove.api.enumarations.ClassificationEnum;
import br.com.prove.api.enumarations.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "tb_macroprocesso" , schema = "svc_rh_prove")
public class Macroprocesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_macroprocesso")
    private Long id;
    @Column(name = "nm_macroprocesso")
    private String nome;
    @Column(name = "ds_macroprocesso")
    private String resumo;

    @Enumerated(EnumType.STRING)
    ClassificationEnum classificacao;
    
    @ManyToMany
	@JoinTable(name = "cargo_macroprocesso", joinColumns = @JoinColumn(name = "id_macroprocesso"),
	inverseJoinColumns = @JoinColumn(name = "id_cargo"))
    @Builder.Default
    private Set<Office> cargos = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "st_macroprocesso")
    StatusEnum status;

}
