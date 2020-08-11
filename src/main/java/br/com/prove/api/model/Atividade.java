package br.com.prove.api.model;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "tb_atividade", schema = "svc_rh_prove")
public class Atividade {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_atividade")
    private Long id;
	@Column(name = "nm_atividade")
	private String nome;
	@Column(name = "ds_atividade")
	private String descricao;

	@Column(name = "posicao")
	private Long posicao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "st_atividade")
	StatusEnum status;

	@ManyToOne
	@JoinColumn(name = "processo", nullable = false)
	private Processo processo;

	@OneToMany(mappedBy = "atividade", cascade = CascadeType.ALL)
	@Builder.Default
	@OrderBy("posicao ASC")
	private List<Tarefa> tarefas = new ArrayList<>();
}
