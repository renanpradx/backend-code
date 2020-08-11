package br.com.prove.api.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "tb_item_processo" , schema = "svc_rh_prove")
public class ItemProcess {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_processo")
	private Long id;

	@Column(name = "nm_item_processo")
	String nome;
}
