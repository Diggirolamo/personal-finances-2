package com.finance.personalfinance.controller;

import com.finance.personalfinance.dto.UscitaDTO;
import com.finance.personalfinance.model.Uscita;
import com.finance.personalfinance.service.UscitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uscite")
@RequiredArgsConstructor
public class UscitaController {

    private final UscitaService uscitaService;

    @PostMapping
    public Uscita create(@RequestBody UscitaDTO dto) {
        return uscitaService.create(dto);
    }

    @GetMapping("/findAll")
    public List<Uscita> getAll() {
        return uscitaService.findAll();
    }

    @GetMapping("/{id}")
    public Uscita getById(@PathVariable Long id) {
        return uscitaService.findById(id);
    }

    @PutMapping("/{id}")
    public Uscita update(@PathVariable Long id, @RequestBody UscitaDTO dto) {
        return uscitaService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        uscitaService.delete(id);
    }

}
