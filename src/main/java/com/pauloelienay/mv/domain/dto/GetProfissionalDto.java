package com.pauloelienay.mv.domain.dto;

import com.pauloelienay.mv.domain.Numero;
import com.pauloelienay.mv.domain.Profissional;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GetProfissionalDto {
    private String nome;
    private String endereco;
    private Numero numero;
    private String funcao;

    public static GetProfissionalDto convertToDto(Profissional profissional) {
        return GetProfissionalDto.builder()
                .nome(profissional.getNome())
                .endereco(profissional.getEndereco())
                .numero(profissional.getNumero())
                .funcao(profissional.getFuncao())
                .build();
    }
}
