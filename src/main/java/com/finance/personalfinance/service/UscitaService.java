package com.finance.personalfinance.service;

import com.finance.personalfinance.dto.UscitaDTO;
import com.finance.personalfinance.model.Uscita;

import java.util.List;

public interface UscitaService {

    Uscita create(UscitaDTO uscita);

    Uscita findById(Long id);

    List<Uscita> findAll();

    Uscita update(Long id, UscitaDTO uscita);

    void delete(Long id);
}
