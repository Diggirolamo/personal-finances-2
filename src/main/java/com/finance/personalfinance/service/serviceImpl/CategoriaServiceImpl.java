package com.finance.personalfinance.service.serviceImpl;

import com.finance.personalfinance.model.Categoria;
import com.finance.personalfinance.repository.CategoriaRepo;
import com.finance.personalfinance.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepo categoriaRepo;

    @Override
    public Categoria create(Categoria categoria) {
        return categoriaRepo.save(categoria);
    }

    @Override
    public Categoria findById(Long id) {
        return categoriaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria non trovata"));
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepo.findAll();
    }

    @Override
    public Categoria update(Long id, Categoria categoria) {
        Categoria existing = findById(id);
        existing.setNome(categoria.getNome());
        return categoriaRepo.save(existing);
    }

    @Override
    public void delete(Long id) {
        categoriaRepo.deleteById(id);
    }
}

