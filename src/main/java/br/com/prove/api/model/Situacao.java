package br.com.prove.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "tb_situacao", schema = "svc_rh_prove")
public class Situacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_situacao")
    private Long id;

    @Column(name = "situacao")
    private String situacao;
}
