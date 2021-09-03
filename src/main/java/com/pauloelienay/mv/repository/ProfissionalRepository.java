package com.pauloelienay.mv.repository;

import com.pauloelienay.mv.domain.Profissional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfissionalRepository extends PagingAndSortingRepository<Profissional, Long> {
    @Query(value = "SELECT * FROM profissionais WHERE nome LIKE %?%", nativeQuery = true)
    Page<Profissional> findAllByNome(Pageable pageable, String nome);
}
