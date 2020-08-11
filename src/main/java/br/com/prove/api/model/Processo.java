package br.com.prove.api.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

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
@Table(name = "tb_processo" , schema = "svc_rh_prove")
public class Processo {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_processo")
    private Long id;
    @Column(name = "nm_processo")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_macroprocesso", nullable = false)
    private Macroprocesso macroprocesso;

    @ManyToMany
	@JoinTable(name = "cargo_processo", joinColumns = @JoinColumn(name = "id_processo"),
	inverseJoinColumns = @JoinColumn(name = "id_cargo"))
    @Builder.Default
    private Set<Office> cargos = new HashSet<>();
    
    @ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "processo_descricao_processo", joinColumns = @JoinColumn(name = "processo_id"),
	inverseJoinColumns = @JoinColumn(name = "descricao_processo_id"))
    @Builder.Default
    private Set<DescriptionProcess> descricoes = new HashSet<>();
    
    @OneToMany(mappedBy = "processo", cascade = CascadeType.ALL)
	@Builder.Default
	@OrderBy("posicao ASC")
	private List<Atividade> atividades = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "st_processo")
    StatusEnum status;
}
