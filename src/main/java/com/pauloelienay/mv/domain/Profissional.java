package com.pauloelienay.mv.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pauloelienay.mv.domain.dto.GetProfissionalDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "profissionais")
public class Profissional implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @Embedded
    private Endereco endereco;

    @Embedded
    private Numero numero;

    private String funcao;

    @ManyToMany
    @JoinTable(name = "profissional_estabelecimento",
            joinColumns = {@JoinColumn(name = "profissional_id")},
            inverseJoinColumns = {@JoinColumn(name = "estabelecimento_id")}
    )
    @JsonBackReference("estabelecimentos")
    private Set<Estabelecimento> estabelecimentos = new HashSet<>();

    public GetProfissionalDto convertToDto() {
        return GetProfissionalDto.convertToDto(this);
    }
}
