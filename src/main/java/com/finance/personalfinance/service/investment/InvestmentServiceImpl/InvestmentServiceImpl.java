package com.finance.personalfinance.service.investment.InvestmentServiceImpl;


import com.finance.personalfinance.model.investment.*;
import com.finance.personalfinance.repository.investment.*;
import com.finance.personalfinance.service.investment.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InvestmentServiceImpl implements InvestmentService {

    private final AssetClassRepository assetClassRepository;
    private final InvestmentRepository investmentRepository;
    private final InvestmentMovementRepository movementRepository;

    @Override
    public AssetClass createAssetClass(AssetClass assetClass) {
        return assetClassRepository.save(assetClass);
    }

    @Override
    public List<AssetClass> getAllAssetClasses() {
        return assetClassRepository.findAll();
    }

    @Override
    public Investment createInvestment(Investment investment) {
        investment.setTotalQuantity(BigDecimal.ZERO);
        investment.setAveragePrice(BigDecimal.ZERO);
        return investmentRepository.save(investment);
    }

    @Override
    public List<Investment> getInvestmentsByUser(Long userId) {
        return investmentRepository.findByUserId(userId);
    }

    @Override
    public Investment getInvestmentById(Long id) {
        return investmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Investment not found: " + id));
    }

    @Override
    public Investment updateInvestment(Long id, Investment updated) {
        Investment existing = getInvestmentById(id);
        existing.setName(updated.getName());
        existing.setTicker(updated.getTicker());
        existing.setAssetClass(updated.getAssetClass());
        return investmentRepository.save(existing);
    }

    @Override
    public void deleteInvestment(Long id) {
        investmentRepository.deleteById(id);
    }

    @Override
    public InvestmentMovement addMovement(Long investmentId, InvestmentMovement movement) {
        Investment investment = getInvestmentById(investmentId);
        movement.setInvestment(investment);

        if (movement.getTotalAmount() == null && movement.getQuantity() != null && movement.getPricePerUnit() != null) {
            movement.setTotalAmount(movement.getPricePerUnit().multiply(movement.getQuantity()));
        }

        InvestmentMovement saved = movementRepository.save(movement);

        // Aggiorna quantità e prezzo medio dell'investimento
        updateInvestmentPosition(investment, saved);

        return saved;
    }

    @Override
    public List<InvestmentMovement> getMovementsByInvestment(Long investmentId) {
        return movementRepository.findByInvestmentId(investmentId);
    }

    private void updateInvestmentPosition(Investment investment, InvestmentMovement movement) {
        // Questo metodo calcola la nuova quantità totale e il nuovo prezzo medio
        // tenendo conto del movimento di tipo acquisto o vendita
    }
}