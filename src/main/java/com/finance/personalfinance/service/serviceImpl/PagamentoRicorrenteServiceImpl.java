package com.finance.personalfinance.service.serviceImpl;

import com.finance.personalfinance.dto.PagamentoRicorrenteDTO;
import com.finance.personalfinance.model.Categoria;
import com.finance.personalfinance.model.PagamentoRicorrente;
import com.finance.personalfinance.model.User;
import com.finance.personalfinance.repository.CategoriaRepository;
import com.finance.personalfinance.repository.PagamentoRicorrenteRepository;
import com.finance.personalfinance.repository.UserRepository;
import com.finance.personalfinance.service.PagamentoRicorrenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoRicorrenteServiceImpl implements PagamentoRicorrenteService {

    private final PagamentoRicorrenteRepository pagamentoRepo;
    private final CategoriaRepository categoriaRepository;
    private final UserRepository userRepository;

    @Override
    public PagamentoRicorrente create(PagamentoRicorrenteDTO dto) {

        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        User user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new RuntimeException("User not found"));

        PagamentoRicorrente pagamentoRicorrente = new PagamentoRicorrente();
        pagamentoRicorrente.setDescrizione(dto.getDescrizione());
        pagamentoRicorrente.setImporto(dto.getImporto());
        pagamentoRicorrente.setProssimaData(dto.getProssimaData());
        pagamentoRicorrente.setFrequenza(dto.getFrequenzaPagamento());
        pagamentoRicorrente.setCategoria(categoria);
        pagamentoRicorrente.setUser(user);

        return pagamentoRepo.save(pagamentoRicorrente);
    }

    @Override
    public PagamentoRicorrente findById(Long id) {
        return pagamentoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurring Payment not found"));
    }

    @Override
    public List<PagamentoRicorrente> findAll() {
        return pagamentoRepo.findAll();
    }

    @Override
    public PagamentoRicorrente update(Long id, PagamentoRicorrenteDTO dto) {

        PagamentoRicorrente pagamento = findById(id);
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        User user = userRepository.findById(dto.getIdUser())
                        .orElseThrow(() -> new RuntimeException("User not found"));

        pagamento.setDescrizione(dto.getDescrizione());
        pagamento.setImporto(dto.getImporto());
        pagamento.setProssimaData(dto.getProssimaData());
        pagamento.setFrequenza(dto.getFrequenzaPagamento());
        pagamento.setCategoria(categoria);
        pagamento.setUser(user);

        return pagamentoRepo.save(pagamento);
    }

    @Override
    public void delete(Long id) {
        pagamentoRepo.deleteById(id);
    }

}

