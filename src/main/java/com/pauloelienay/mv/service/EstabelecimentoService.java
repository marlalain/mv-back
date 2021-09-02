package com.pauloelienay.mv.service;

import com.pauloelienay.mv.domain.Estabelecimento;
import com.pauloelienay.mv.exception.EntityNotFoundException;
import com.pauloelienay.mv.repository.EstabelecimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        repository.deleteById(id);
    }

    public Estabelecimento update(long id, Estabelecimento estabelecimento) {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Estabelecimento could not be found."));
        estabelecimento.setId(id);
        repository.save(estabelecimento);
        return estabelecimento;
    }
}
