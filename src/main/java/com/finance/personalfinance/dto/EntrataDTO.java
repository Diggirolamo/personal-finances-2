package com.finance.personalfinance.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EntrataDTO {

    private String descrizione;
    private BigDecimal importo;
    private LocalDate data;
    private Long idCategoria;

}
