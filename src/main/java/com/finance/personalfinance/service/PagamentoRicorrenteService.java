package com.finance.personalfinance.service;

import com.finance.personalfinance.dto.PagamentoRicorrenteDTO;
import com.finance.personalfinance.model.PagamentoRicorrente;

import java.util.List;

public interface PagamentoRicorrenteService {

    PagamentoRicorrente create(PagamentoRicorrenteDTO entrata);
    PagamentoRicorrente findById(Long id);
    List<PagamentoRicorrente> findAll();
    PagamentoRicorrente update(Long id, PagamentoRicorrenteDTO pagamentoRicorrente);
    void delete(Long id);

}
