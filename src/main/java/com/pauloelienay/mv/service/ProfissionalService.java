package com.pauloelienay.mv.service;

import com.pauloelienay.mv.domain.Profissional;
import com.pauloelienay.mv.exception.EntityNotFoundException;
import com.pauloelienay.mv.repository.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfissionalService {
    private final ProfissionalRepository repository;

    public Page<Profissional> getPageableProfissionais(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Profissional getProfissionalById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profissional could not be found."));
    }
}
