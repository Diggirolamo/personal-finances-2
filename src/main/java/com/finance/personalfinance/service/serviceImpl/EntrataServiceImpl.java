package com.finance.personalfinance.service.serviceImpl;

import com.finance.personalfinance.dto.EntrataDTO;
import com.finance.personalfinance.model.Categoria;
import com.finance.personalfinance.model.Entrata;
import com.finance.personalfinance.repository.CategoriaRepo;
import com.finance.personalfinance.repository.EntrataRepo;
import com.finance.personalfinance.service.EntrataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntrataServiceImpl implements EntrataService {

    private final EntrataRepo entrataRepository;
    private final CategoriaRepo categoriaRepository;

    @Override
    public Entrata create(EntrataDTO dto) {

        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria non trovata"));

        Entrata entrata = new Entrata();
        entrata.setDescrizione(dto.getDescrizione());
        entrata.setImporto(dto.getImporto());
        entrata.setData(dto.getData());
        entrata.setCategoria(categoria);

        return entrataRepository.save(entrata);
    }

    @Override
    public Entrata findById(Long id) {
        return entrataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrata non trovata"));
    }

    @Override
    public List<Entrata> findAll() {
        return entrataRepository.findAll();
    }

    @Override
    public Entrata update(Long id, EntrataDTO dto) {

        Entrata entrata = findById(id);

        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria non trovata"));

        entrata.setDescrizione(dto.getDescrizione());
        entrata.setImporto(dto.getImporto());
        entrata.setData(dto.getData());
        entrata.setCategoria(categoria);

        return entrataRepository.save(entrata);
    }

    @Override
    public void delete(Long id) {
        entrataRepository.deleteById(id);
    }
}
