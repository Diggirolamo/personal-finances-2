package com.finance.personalfinance.service;

import com.finance.personalfinance.model.Categoria;

import java.util.List;
public interface CategoriaService {

    Categoria create(Categoria categoria);
    Categoria findById(Long id);
    List<Categoria> findAll();
    Categoria update(Long id, Categoria categoria);
    void delete(Long id);
}
