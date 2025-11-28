package com.finance.personalfinance.repository;

import com.finance.personalfinance.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepo extends JpaRepository<Categoria, Long> {

}
