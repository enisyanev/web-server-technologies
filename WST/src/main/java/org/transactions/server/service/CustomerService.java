package org.transactions.server.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.transactions.server.controller.dto.CustomerDto;
import org.transactions.server.entity.Customer;
import org.transactions.server.exception.NotFoundException;
import org.transactions.server.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository repository;

    public Customer get(UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Customer with id " + id + " not found"));
    }

    public Customer create(CustomerDto customer) {
        Customer newCustomer = new Customer();
        newCustomer.setId(UUID.randomUUID());
        newCustomer.setFirstName(customer.getFirstName());
        newCustomer.setLastName(customer.getLastName());
        newCustomer.setEmail(customer.getEmail());

        return repository.save(newCustomer);
    }

    public Customer update(UUID id, CustomerDto customer) {
        Customer existing = get(id);
        existing.setEmail(customer.getEmail());
        existing.setFirstName(customer.getFirstName());
        existing.setLastName(customer.getLastName());
        return repository.save(existing);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
