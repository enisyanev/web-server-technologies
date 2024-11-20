package org.transactions.server.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.transactions.server.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    void deleteByCustomerId(UUID customerId);
}
