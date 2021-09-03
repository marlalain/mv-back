package com.pauloelienay.mv.repository;

import com.pauloelienay.mv.domain.Profissional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfissionalRepository extends PagingAndSortingRepository<Profissional, Long> {
    Page<Profissional> findAllByNomeContains(Pageable pageable, String nome);
}
