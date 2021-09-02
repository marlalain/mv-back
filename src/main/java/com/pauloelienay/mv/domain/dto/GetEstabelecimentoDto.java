package com.pauloelienay.mv.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pauloelienay.mv.domain.Endereco;
import com.pauloelienay.mv.domain.Estabelecimento;
import com.pauloelienay.mv.domain.Profissional;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetEstabelecimentoDto {
    private String nome;
    private Endereco endereco;
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
