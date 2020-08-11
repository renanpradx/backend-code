package br.com.prove.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "tb_tarefa" , schema = "svc_rh_prove")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarefa")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "atividade", nullable = false)
    private Atividade atividade;
    @Column(name = "nm_tarefa")
    private String nome;
    @Column(name = "ds_tarefa")
    private String descricao;
    
    @OneToMany
    private List<MediaFile> arquivo;

    private boolean inicio;

    private int posicao;

    @Enumerated(EnumType.STRING)
    @Column(name = "st_tarefa")
    StatusEnum status;
}
