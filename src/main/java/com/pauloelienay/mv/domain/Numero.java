package com.pauloelienay.mv.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@RequiredArgsConstructor
@Embeddable
public class Numero {
    private String residencial;
    private String celular;
}
