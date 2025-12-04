package com.finance.personalfinance.service.serviceImpl;

import com.finance.personalfinance.dto.EntrataDTO;
import com.finance.personalfinance.model.Categoria;
import com.finance.personalfinance.model.Entrata;
import com.finance.personalfinance.model.User;
import com.finance.personalfinance.repository.CategoriaRepository;
import com.finance.personalfinance.repository.EntrataRepository;
import com.finance.personalfinance.repository.UserRepository;
import com.finance.personalfinance.service.EntrataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntrataServiceImpl implements EntrataService {

    private final EntrataRepository entrataRepository;
    private final CategoriaRepository categoriaRepository;
    private final UserRepository userRepository;

    @Override
    public Entrata create(EntrataDTO dto) {

        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        User user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Entrata entrata = new Entrata();
        entrata.setDescrizione(dto.getDescrizione());
        entrata.setImporto(dto.getImporto());
        entrata.setData(dto.getData());
        entrata.setCategoria(categoria);
        entrata.setUser(user);

        return entrataRepository.save(entrata);
    }

    @Override
    public Entrata findById(Long id) {
        return entrataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incoming not found"));
    }

    @Override
    public List<Entrata> findAll() {
        return entrataRepository.findAll();
    }

    @Override
    public Entrata update(Long id, EntrataDTO dto) {

        Entrata entrata = findById(id);
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        User user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new RuntimeException("User not found"));

        entrata.setDescrizione(dto.getDescrizione());
        entrata.setImporto(dto.getImporto());
        entrata.setData(dto.getData());
        entrata.setCategoria(categoria);
        entrata.setUser(user);

        return entrataRepository.save(entrata);
    }

    @Override
    public void delete(Long id) {
        entrataRepository.deleteById(id);
    }
}
