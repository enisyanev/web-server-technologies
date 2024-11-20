package org.transactions.server.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "TRANSACTION_DETAILS")
public class TransactionDetails {

    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "SOURCE")
    private String source;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
    private Transaction transaction;
}
