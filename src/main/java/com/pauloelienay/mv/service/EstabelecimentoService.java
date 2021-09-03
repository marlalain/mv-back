package com.pauloelienay.mv.service;

import com.pauloelienay.mv.domain.Estabelecimento;
import com.pauloelienay.mv.exception.EntityBeingUsedException;
import com.pauloelienay.mv.exception.EntityNotFoundException;
import com.pauloelienay.mv.repository.EstabelecimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstabelecimentoService {
    private final EstabelecimentoRepository repository;

    public Page<Estabelecimento> getPageableEstabelecimentos(Pageable pageable, String nome) {
        if (nome == null) return repository.findAll(pageable);

        return repository.findAllByNomeContains(pageable, nome);
    }

    public Estabelecimento getEstabelecimentoById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estabelecimento não pôde ser encontrado."));
    }

    public Estabelecimento create(Estabelecimento estabelecimento) {
        return repository.save(estabelecimento);
    }

    public void deleteEstabelecimentoById(long id) {
        repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Estabelecimento não pôde ser encontrado."));
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityBeingUsedException
                    ("Estabelecimento está sendo usado. Remova Profissionais dele e tente novamente.");
        }
    }

    public Estabelecimento update(long id, Estabelecimento estabelecimento) {
        repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Estabelecimento não pôde ser encontrado."));
        estabelecimento.setId(id);
        repository.save(estabelecimento);
        return estabelecimento;
    }
}
