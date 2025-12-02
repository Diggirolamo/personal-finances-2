package com.finance.personalfinance.repository.investment;


import com.finance.personalfinance.model.investment.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    List<Investment> findByUserId(Long userId);
}