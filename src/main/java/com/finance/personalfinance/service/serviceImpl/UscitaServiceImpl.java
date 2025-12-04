package com.finance.personalfinance.service.serviceImpl;

import com.finance.personalfinance.dto.UscitaDTO;
import com.finance.personalfinance.model.Categoria;
import com.finance.personalfinance.model.Uscita;
import com.finance.personalfinance.model.User;
import com.finance.personalfinance.repository.CategoriaRepository;
import com.finance.personalfinance.repository.UscitaRepository;
import com.finance.personalfinance.repository.UserRepository;
import com.finance.personalfinance.service.UscitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UscitaServiceImpl implements UscitaService {

    private final UscitaRepository uscitaRepository;

    private final CategoriaRepository categoriaRepository;
    private final UserRepository userRepository;

    @Override
    public Uscita create(UscitaDTO dto) {

        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        User user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Uscita uscita = new Uscita();
        uscita.setDescrizione(dto.getDescrizione());
        uscita.setImporto(dto.getImporto());
        uscita.setData(dto.getData());
        uscita.setCategoria(categoria);
        uscita.setUser(user);

        return uscitaRepository.save(uscita);
    }

    @Override
    public Uscita findById(Long id) {
        return uscitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    @Override
    public List<Uscita> findAll() {
        return uscitaRepository.findAll();
    }

    @Override
    public Uscita update(Long id, UscitaDTO dto) {

        Uscita uscita = findById(id);
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        User user = userRepository.findById(dto.getIdUser())
                        .orElseThrow(() -> new RuntimeException("User not found"));

        uscita.setDescrizione(dto.getDescrizione());
        uscita.setImporto(dto.getImporto());
        uscita.setData(dto.getData());
        uscita.setCategoria(categoria);
        uscita.setUser(user);

        return uscitaRepository.save(uscita);
    }

    @Override
    public void delete(Long id) {
        uscitaRepository.deleteById(id);
    }
}
