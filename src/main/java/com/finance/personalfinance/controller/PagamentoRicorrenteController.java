package com.finance.personalfinance.controller;

import com.finance.personalfinance.dto.PagamentoRicorrenteDTO;
import com.finance.personalfinance.model.PagamentoRicorrente;
import com.finance.personalfinance.service.PagamentoRicorrenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentoRicorrente")
@RequiredArgsConstructor
public class PagamentoRicorrenteController {

    private final PagamentoRicorrenteService pagamentoService;

    @PostMapping
    public PagamentoRicorrente create(@RequestBody PagamentoRicorrenteDTO dto) {
        return pagamentoService.create(dto);
    }

    @GetMapping("/findAll")
    public List<PagamentoRicorrente> getAll() {
        return pagamentoService.findAll();
    }

    @GetMapping("/{id}")
    public PagamentoRicorrente getById(@PathVariable Long id) {
        return pagamentoService.findById(id);
    }

    @PutMapping("/{id}")
    public PagamentoRicorrente update(@PathVariable Long id, @RequestBody PagamentoRicorrenteDTO dto) {
        return pagamentoService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pagamentoService.delete(id);
    }
}
