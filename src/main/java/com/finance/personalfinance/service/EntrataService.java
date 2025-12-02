package com.finance.personalfinance.service;

import com.finance.personalfinance.dto.EntrataDTO;
import com.finance.personalfinance.model.Entrata;

import java.util.List;

public interface EntrataService {
    Entrata create(EntrataDTO entrata);
    Entrata findById(Long id);
    List<Entrata> findAll();
    Entrata update(Long id, EntrataDTO entrata);
    void delete(Long id);
}
