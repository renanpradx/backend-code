package br.com.prove.api.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_descricao_processo", schema = "svc_rh_prove")
public class DescriptionProcess {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_descricao_processo")
	private Long id;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private ItemProcess itemProcesso;

	@Column(name = "ds_descricao_processo")
	String descricao;
}
