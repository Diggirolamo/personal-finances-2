package com.finance.personalfinance.repository.investment;

import com.finance.personalfinance.model.investment.AssetClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssetClassRepository extends JpaRepository<AssetClass, Long> {

    Optional<AssetClass> findByCode(String code);
}