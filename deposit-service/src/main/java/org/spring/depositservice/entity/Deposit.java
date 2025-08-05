package org.spring.depositservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Data
@NoArgsConstructor
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long depositId;

    private BigDecimal amount;

    private Long billId;

    private OffsetDateTime createdDate;

    private String mail;

    public Deposit(String mail, OffsetDateTime createdDate, Long billId, BigDecimal amount) {
        this.mail = mail;
        this.createdDate = createdDate;
        this.billId = billId;
        this.amount = amount;
    }
}
