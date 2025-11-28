package com.finance.personalfinance.service.serviceImpl;

import com.finance.personalfinance.dto.PagamentoRicorrenteDTO;
import com.finance.personalfinance.model.Categoria;
import com.finance.personalfinance.model.PagamentoRicorrente;
import com.finance.personalfinance.repository.CategoriaRepo;
import com.finance.personalfinance.repository.PagamentoRicorrenteRepo;
import com.finance.personalfinance.service.PagamentoRicorrenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoRicorrenteServiceImpl implements PagamentoRicorrenteService {

    private final PagamentoRicorrenteRepo pagamentoRepo;

    private final CategoriaRepo categoriaRepo;

    @Override
    public PagamentoRicorrente create(PagamentoRicorrenteDTO dto) {

        Categoria categoria = categoriaRepo.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria non trovata"));

        PagamentoRicorrente pagamentoRicorrente = new PagamentoRicorrente();
        pagamentoRicorrente.setDescrizione(dto.getDescrizione());
        pagamentoRicorrente.setImporto(dto.getImporto());
        pagamentoRicorrente.setProssimaData(dto.getProssimaData());
        pagamentoRicorrente.setFrequenza(dto.getFrequenzaPagamento());
        pagamentoRicorrente.setCategoria(categoria);

        return pagamentoRepo.save(pagamentoRicorrente);
    }

    @Override
    public PagamentoRicorrente findById(Long id) {
        return pagamentoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento Ricorrente non trovata"));
    }

    @Override
    public List<PagamentoRicorrente> findAll() {
        return pagamentoRepo.findAll();
    }

    @Override
    public PagamentoRicorrente update(Long id, PagamentoRicorrenteDTO dto) {

        PagamentoRicorrente pagamento = findById(id);

        Categoria categoria = categoriaRepo.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria non trovata"));

        pagamento.setDescrizione(dto.getDescrizione());
        pagamento.setImporto(dto.getImporto());
        pagamento.setProssimaData(dto.getProssimaData());
        pagamento.setFrequenza(dto.getFrequenzaPagamento());
        pagamento.setCategoria(categoria);

        return pagamentoRepo.save(pagamento);
    }

    @Override
    public void delete(Long id) {
        pagamentoRepo.deleteById(id);
    }

}

