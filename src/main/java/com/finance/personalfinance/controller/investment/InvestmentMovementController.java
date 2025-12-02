package com.finance.personalfinance.controller.investment;

import com.finance.personalfinance.model.investment.InvestmentMovement;
import com.finance.personalfinance.service.investment.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investments/{investmentId}/movements")
@RequiredArgsConstructor
public class InvestmentMovementController {

    private final InvestmentService investmentService;

    @PostMapping
    public ResponseEntity<InvestmentMovement> addMovement(
            @PathVariable Long investmentId,
            @RequestBody InvestmentMovement movement
    ) {
        return ResponseEntity.ok(investmentService.addMovement(investmentId, movement));
    }

    @GetMapping
    public ResponseEntity<List<InvestmentMovement>> getMovements(@PathVariable Long investmentId) {
        return ResponseEntity.ok(investmentService.getMovementsByInvestment(investmentId));
    }
}
