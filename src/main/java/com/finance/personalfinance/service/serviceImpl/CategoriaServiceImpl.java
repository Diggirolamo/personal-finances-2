package com.finance.personalfinance.service.serviceImpl;

import com.finance.personalfinance.model.Categoria;
import com.finance.personalfinance.model.User;
import com.finance.personalfinance.repository.CategoriaRepository;
import com.finance.personalfinance.repository.UserRepository;
import com.finance.personalfinance.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final UserRepository userRepository;


    @Override
    public Categoria create(Categoria categoria) {

        if (categoria.getUser() != null && categoria.getUser().getId() != null) {
            User realUser = userRepository.findById(categoria.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            categoria.setUser(realUser);
        }

        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria update(Long id, Categoria categoria) {
        Categoria existing = findById(id);
        existing.setNome(categoria.getNome());

        if (categoria.getUser() != null && categoria.getUser().getId() != null) {
            User realUser = userRepository.findById(categoria.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            existing.setUser(realUser);
        }

        return categoriaRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }
}

