package org.transactions.server.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.transactions.server.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
