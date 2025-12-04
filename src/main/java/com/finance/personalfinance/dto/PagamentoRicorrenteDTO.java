package com.finance.personalfinance.dto;

import com.finance.personalfinance.model.FrequenzaPagamento;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PagamentoRicorrenteDTO {

    private String descrizione;
    private BigDecimal importo;
    private LocalDate prossimaData;
    private FrequenzaPagamento frequenzaPagamento;
    private Long idCategoria;
    private Long idUser;
}
