package com.finance.personalfinance.controller;

import com.finance.personalfinance.dto.EntrataDTO;
import com.finance.personalfinance.model.Entrata;
import com.finance.personalfinance.service.EntrataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrate")
@RequiredArgsConstructor
public class EntrataController {

    private final EntrataService entrataService;

    @PostMapping
    public Entrata create(@RequestBody EntrataDTO dto) {
        return entrataService.create(dto);
    }

    @GetMapping("/findAll")
    public List<Entrata> getAll() {
        return entrataService.findAll();
    }

    @GetMapping("/{id}")
    public Entrata getById(@PathVariable Long id) {
        return entrataService.findById(id);
    }

    @PutMapping("/{id}")
    public Entrata update(@PathVariable Long id, @RequestBody EntrataDTO dto) {
        return entrataService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        entrataService.delete(id);
    }
}
