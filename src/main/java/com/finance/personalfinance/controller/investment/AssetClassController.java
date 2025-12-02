package com.finance.personalfinance.controller.investment;

import com.finance.personalfinance.model.investment.AssetClass;
import com.finance.personalfinance.service.investment.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asset-classes")
@RequiredArgsConstructor
public class AssetClassController {

    private final InvestmentService investmentService;

    @PostMapping
    public ResponseEntity<AssetClass> create(@RequestBody AssetClass request) {
        return ResponseEntity.ok(investmentService.createAssetClass(request));
    }

    @GetMapping
    public ResponseEntity<List<AssetClass>> findAll() {
        return ResponseEntity.ok(investmentService.getAllAssetClasses());
    }
}
