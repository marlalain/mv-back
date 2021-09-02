package com.pauloelienay.mv.domain;

import com.pauloelienay.mv.domain.dto.GetEstabelecimentoDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "estabelecimentos")
public class Estabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private String endereco;

    private String telefone;

    public GetEstabelecimentoDto convertToDto() {
        return GetEstabelecimentoDto.convertToDto(this);
    }
}
