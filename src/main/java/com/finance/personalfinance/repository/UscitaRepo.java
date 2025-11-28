package com.finance.personalfinance.repository;

import com.finance.personalfinance.model.Uscita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UscitaRepo extends JpaRepository<Uscita, Long> {
}
