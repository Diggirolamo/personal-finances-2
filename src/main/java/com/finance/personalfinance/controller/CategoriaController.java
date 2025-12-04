package com.finance.personalfinance.controller;

import com.finance.personalfinance.model.Categoria;
import com.finance.personalfinance.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorie")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping("/create")
    public Categoria create(@RequestBody Categoria c) {
        return categoriaService.create(c);
    }

    @GetMapping("/findAll")
    public List<Categoria> getAll() {
        return categoriaService.findAll();
    }

    @GetMapping("/findBy/{id}")
    public Categoria getById(@PathVariable Long id) {
        return categoriaService.findById(id);
    }

    @PutMapping("/update/{id}")
    public Categoria update(@PathVariable Long id, @RequestBody Categoria c) {
        return categoriaService.update(id, c);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        categoriaService.delete(id);
    }

}

