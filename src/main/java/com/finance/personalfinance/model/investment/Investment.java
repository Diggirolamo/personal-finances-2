package com.finance.personalfinance.model.investment;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "investment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Per ora teniamo un riferimento semplice all'utente tramite id
    // In futuro si potrà mappare con l'entity User di Dev1
    @Column(nullable = false)
    private Long userId;

    // Es: "VWCE", "AAPL", "BTC"
    @Column(nullable = false)
    private String ticker;

    // Nome leggibile dell'investimento
    @Column(nullable = false)
    private String name;

    // Prezzo medio di carico per unità, aggiornato in base ai movimenti
    @Column(precision = 19, scale = 6)
    private BigDecimal averagePrice;

    // Quantità totale attualmente detenuta
    @Column(precision = 19, scale = 6)
    private BigDecimal totalQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_class_id", nullable = false)
    private AssetClass assetClass;
}
