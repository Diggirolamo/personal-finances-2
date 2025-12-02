package com.finance.personalfinance.controller.investment;

import com.finance.personalfinance.model.investment.Investment;
import com.finance.personalfinance.service.investment.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investments")
@RequiredArgsConstructor
public class InvestmentController {

    private final InvestmentService investmentService;

    @PostMapping
    public ResponseEntity<Investment> create(@RequestBody Investment investment) {
        return ResponseEntity.ok(investmentService.createInvestment(investment));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Investment>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(investmentService.getInvestmentsByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investment> getById(@PathVariable Long id) {
        return ResponseEntity.ok(investmentService.getInvestmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Investment> update(@PathVariable Long id, @RequestBody Investment updated) {
        return ResponseEntity.ok(investmentService.updateInvestment(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        investmentService.deleteInvestment(id);
        return ResponseEntity.noContent().build();
    }
}
