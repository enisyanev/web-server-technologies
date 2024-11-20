package org.transactions.server.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.transactions.server.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    void deleteByCustomerId(UUID customerId);
    void deleteByAccountId(UUID accountId);

}
