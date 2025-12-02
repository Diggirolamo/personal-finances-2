package com.finance.personalfinance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoRicorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descrizione;

    @Column(nullable=false)
    private BigDecimal importo;

    @Column(nullable=false)
    private LocalDate prossimaData;

    @Enumerated(EnumType.STRING)
    private FrequenzaPagamento frequenza; // MENSILE / SETTIMANALE / ANNUALE etc.

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Categoria categoria;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;
}

