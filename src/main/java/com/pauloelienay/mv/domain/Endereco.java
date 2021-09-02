package com.pauloelienay.mv.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@RequiredArgsConstructor
@Embeddable
public class Endereco {
    private String rua;
    private String bairro;
    private Integer numero;
}
