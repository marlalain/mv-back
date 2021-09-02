package com.pauloelienay.mv.domain;

import com.pauloelienay.mv.domain.dto.GetProfissionalDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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

    private String endereco;

    @Embedded
    private Numero numero;

    private String funcao;

    public GetProfissionalDto convertToDto() {
        return GetProfissionalDto.convertToDto(this);
    }
}
