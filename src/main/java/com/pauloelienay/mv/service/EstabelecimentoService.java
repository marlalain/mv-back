package com.pauloelienay.mv.service;

import com.pauloelienay.mv.domain.Estabelecimento;
import com.pauloelienay.mv.exception.EntityBeingUsedException;
import com.pauloelienay.mv.exception.EntityNotFoundException;
import com.pauloelienay.mv.repository.EstabelecimentoRepository;
import lombok.RequiredArgsConstructor;
import org.omg.SendingContext.RunTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class EstabelecimentoService {
    private final EstabelecimentoRepository repository;

    public Page<Estabelecimento> getPageableEstabelecimentos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Estabelecimento getEstabelecimentoById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estabelecimento could not be found."));
    }

    public Estabelecimento create(Estabelecimento estabelecimento) {
        return repository.save(estabelecimento);
    }

    public void deleteEstabelecimentoById(long id) {
        try {
            repository.deleteById(id);
        } catch (Exception _e) {
            throw new EntityBeingUsedException
                    ("Estabelecimento is being used. Remove Profissionais from it and try again.");
        }
    }

    public Estabelecimento update(long id, Estabelecimento estabelecimento) {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Estabelecimento could not be found."));
        estabelecimento.setId(id);
        repository.save(estabelecimento);
        return estabelecimento;
    }
}
