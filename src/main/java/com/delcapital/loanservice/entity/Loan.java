package com.delcapital.loanservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private String customerName;

    private BigDecimal principalOutstanding;

    private BigDecimal annualInterestRate;

    private BigDecimal totalInterestAccrued = BigDecimal.ZERO;

    @Column(columnDefinition = "DATETIME(0)")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "DATETIME(0)")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
