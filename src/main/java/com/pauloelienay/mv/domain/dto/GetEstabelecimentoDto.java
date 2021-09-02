package com.pauloelienay.mv.domain.dto;

import com.pauloelienay.mv.domain.Estabelecimento;
import com.pauloelienay.mv.domain.Profissional;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
public class GetEstabelecimentoDto {
    private String nome;
    private String endereco;
    private String telefone;
    private Set<Profissional> profissionais;

    public static GetEstabelecimentoDto convertToDto(Estabelecimento estabelecimento) {
        return GetEstabelecimentoDto.builder()
                .nome(estabelecimento.getNome())
                .endereco(estabelecimento.getEndereco())
                .telefone(estabelecimento.getTelefone())
                .profissionais(estabelecimento.getProfissionais())
                .build();
    }
}
