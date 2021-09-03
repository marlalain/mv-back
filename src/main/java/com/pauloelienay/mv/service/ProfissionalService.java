package com.pauloelienay.mv.service;

import com.pauloelienay.mv.domain.Profissional;
import com.pauloelienay.mv.domain.dto.GetProfissionalDto;
import com.pauloelienay.mv.exception.EntityBeingUsedException;
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

    public Page<Profissional> getPageableProfissionais(Pageable pageable, String nome) {
        if (nome == null || nome.isEmpty()) return repository.findAll(pageable);

        return repository.findAllByNome(pageable, nome);
    }

    public GetProfissionalDto getProfissionalDtoById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profissional could not be found.")).convertToDto();
    }

    public Profissional getProfissionalById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profissional could not be found."));
    }

    public Profissional create(Profissional profissional) {
        return repository.save(profissional);
    }

    public void deleteProfissionalById(long id) {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Profissional could not be found."));
        repository.deleteById(id);
    }

    public Profissional update(long id, Profissional profissional) {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Profissional could not be found."));
        profissional.setId(id);
        repository.save(profissional);
        return profissional;
    }
}
