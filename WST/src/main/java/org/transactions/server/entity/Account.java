package org.transactions.server.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor @Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Transaction> transactions = new HashSet<>();


}
