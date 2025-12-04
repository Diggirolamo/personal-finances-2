package com.finance.personalfinance.model;

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

public class Uscita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descrizione;

    @Column(nullable=false)
    private BigDecimal importo;

    @Column(nullable=false)
    private LocalDate data;

    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
