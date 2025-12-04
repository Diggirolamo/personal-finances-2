package com.finance.personalfinance.model.investment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "asset_class")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AssetClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Esempi: "STOCK", "ETF", "BOND", "CRYPTO"
    @Column(nullable = false, unique = true)
    private String code;

    // Nome leggibile: "Azioni", "ETF", "Obbligazioni", ecc
    @Column(nullable = false)
    private String name;

    // Descrizione testuale facoltativa
    private String description;
}