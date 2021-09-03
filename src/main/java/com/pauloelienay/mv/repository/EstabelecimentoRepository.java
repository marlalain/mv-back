package com.pauloelienay.mv.repository;

import com.pauloelienay.mv.domain.Estabelecimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EstabelecimentoRepository extends PagingAndSortingRepository<Estabelecimento, Long> {
    Page<Estabelecimento> findAllByNomeContains(Pageable pageable, String nome);
}
