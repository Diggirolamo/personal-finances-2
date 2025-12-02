package com.finance.personalfinance.service.investment;

import com.finance.personalfinance.model.investment.AssetClass;
import com.finance.personalfinance.model.investment.Investment;
import com.finance.personalfinance.model.investment.InvestmentMovement;

import java.util.List;

public interface InvestmentService {

    // AssetClass
    AssetClass createAssetClass(AssetClass assetClass);
    List<AssetClass> getAllAssetClasses();

    // Investment
    Investment createInvestment(Investment investment);
    List<Investment> getInvestmentsByUser(Long userId);
    Investment getInvestmentById(Long id);
    Investment updateInvestment(Long id, Investment updated);
    void deleteInvestment(Long id);

    // Movimenti
    InvestmentMovement addMovement(Long investmentId, InvestmentMovement movement);
    List<InvestmentMovement> getMovementsByInvestment(Long investmentId);

    // Calcoli base da usare anche per le API dei grafici
    // Esempio: totale investito, valore di carico, ecc
}
