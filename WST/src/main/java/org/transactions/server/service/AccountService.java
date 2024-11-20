package org.transactions.server.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.transactions.server.controller.dto.AccountDto;
import org.transactions.server.entity.Account;
import org.transactions.server.entity.Customer;
import org.transactions.server.exception.NotFoundException;
import org.transactions.server.repository.AccountRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;

    public Account get(UUID id) {
        return accountRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Account with id " + id + " not found"));
    }

    public Account create(AccountDto account) {
        Account newAccount = new Account();
        newAccount.setId(UUID.randomUUID());
        newAccount.setBalance(BigDecimal.ZERO);
        newAccount.setCurrency(account.getCurrency());

        Customer customer = customerService.get(account.getCustomerId());
        newAccount.setCustomer(customer);

        return accountRepository.save(newAccount);
    }

    public Account update(UUID id, AccountDto account) {
        Account existingAccount = get(id);
        existingAccount.setCurrency(account.getCurrency());

        return accountRepository.save(existingAccount);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void delete(UUID id) {
        accountRepository.deleteById(id);
    }
}
