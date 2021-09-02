package com.pauloelienay.mv.domain.dto;

import com.pauloelienay.mv.domain.Estabelecimento;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GetEstabelecimentoDto {
    private String nome;
    private String endereco;
    private String telefone;

    public static GetEstabelecimentoDto convertToDto(Estabelecimento estabelecimento) {
        return GetEstabelecimentoDto.builder()
                .nome(estabelecimento.getNome())
                .endereco(estabelecimento.getEndereco())
                .telefone(estabelecimento.getTelefone())
                .build();
    }
}
