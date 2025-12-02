package com.finance.personalfinance.repository.investment;

import com.finance.personalfinance.model.investment.InvestmentMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentMovementRepository extends JpaRepository<InvestmentMovement, Long> {

    List<InvestmentMovement> findByInvestmentId(Long investmentId);

    List<InvestmentMovement> findByInvestment_UserId(Long userId);
}