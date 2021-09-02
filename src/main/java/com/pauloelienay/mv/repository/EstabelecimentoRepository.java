package com.pauloelienay.mv.repository;

import com.pauloelienay.mv.domain.Estabelecimento;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EstabelecimentoRepository extends PagingAndSortingRepository<Estabelecimento, Long> {
}
