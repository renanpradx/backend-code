package br.com.prove.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

public class Funcionarios {

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    @Entity
    @Table(name = "tb_funcionario", schema = "svc_rh_prove")
    public class Funcionario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_funcionario")
        private Long id;

        @Column(name = "nm_funcionario")
        private String primeiroNome;

        @Column(name = "nm_funcionario")
        private String segundoNome;

        @Column(name = "nm_funcionario")
        private String ultimoNome;

        @Column(name = "data_funcionario")
        private Long dataNascimento;

        @Column(name = "cpf_funcionario")
        private String cpf;

        @Column(name = "rg_funcionario")
        private String rg;

        @Column(name = "endereco_funcionario")
        private String endereco;

        @Column(name = "cep_funcionario")
        private String cep;

        @Column(name = "cidade_funcionario")
        private String cidade;

        @Column(name = "fone_funcionario")
        private String fone;

        @Column(name = "codep_funcionario")
        private Long codigoDepartamento;

        @Column(name = "funcao_funcionario")
        private String funcao;

        @Column(name = "salario_funcionario")
        private Float salario;


    }
}
