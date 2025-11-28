package com.finance.personalfinance.service.serviceImpl;

import com.finance.personalfinance.dto.UscitaDTO;
import com.finance.personalfinance.model.Categoria;
import com.finance.personalfinance.model.Uscita;
import com.finance.personalfinance.repository.CategoriaRepo;
import com.finance.personalfinance.repository.UscitaRepo;
import com.finance.personalfinance.service.UscitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UscitaServiceImpl implements UscitaService {

    private final UscitaRepo uscitaRepo;

    private final CategoriaRepo categoriaRepo;

    @Override
    public Uscita create(UscitaDTO dto) {

        Categoria categoria = categoriaRepo.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria non trovata"));

        Uscita uscita = new Uscita();
        uscita.setDescrizione(dto.getDescrizione());
        uscita.setImporto(dto.getImporto());
        uscita.setData(dto.getData());
        uscita.setCategoria(categoria);

        return uscitaRepo.save(uscita);
    }

    @Override
    public Uscita findById(Long id) {
        return uscitaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Uscita non trovata"));
    }

    @Override
    public List<Uscita> findAll() {
        return uscitaRepo.findAll();
    }

    @Override
    public Uscita update(Long id, UscitaDTO dto) {

        Uscita uscita = findById(id);

        Categoria categoria = categoriaRepo.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria non trovata"));

        uscita.setDescrizione(dto.getDescrizione());
        uscita.setImporto(dto.getImporto());
        uscita.setData(dto.getData());
        uscita.setCategoria(categoria);

        return uscitaRepo.save(uscita);
    }

    @Override
    public void delete(Long id) {
        uscitaRepo.deleteById(id);
    }
}
