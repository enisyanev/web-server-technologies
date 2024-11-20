package org.transactions.server.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @Column(name = "ID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "INSERT_TIME")
    @CreationTimestamp
    private OffsetDateTime insertTime;

    @OneToOne(mappedBy = "transaction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TransactionDetails transactionDetails;

}
