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
@Table(name = "FNC_ADP" , schema = "svc_rh_colab")
public class Office {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "cd_fncadp")
	 private Long id;

	 @Column(name = "nm_fncadp")
	 String descricao;
	 
	 @Column(name = "st_fncadp_ina")
	 private String status;
}
