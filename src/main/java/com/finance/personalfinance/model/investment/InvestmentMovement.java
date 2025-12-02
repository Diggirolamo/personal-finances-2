package com.finance.personalfinance.model.investment;

import com.finance.personalfinance.model.investment.enums.MovementType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "investment_movement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestmentMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Acquisto o vendita
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investment_id", nullable = false)
    private Investment investment;

    // Quantità acquistata o venduta
    @Column(nullable = false, precision = 19, scale = 6)
    private BigDecimal quantity;

    // Prezzo per unità
    @Column(nullable = false, precision = 19, scale = 6)
    private BigDecimal pricePerUnit;

    // Data del movimento
    @Column(nullable = false)
    private LocalDateTime dateTime;

    // Eventuale note
    private String note;

    // Totale del movimento = quantity * pricePerUnit
    @Column(precision = 19, scale = 6)
    private BigDecimal totalAmount;
}
