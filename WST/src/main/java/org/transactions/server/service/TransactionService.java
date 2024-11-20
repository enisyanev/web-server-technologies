package org.transactions.server.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.transactions.server.controller.dto.TransactionDto;
import org.transactions.server.entity.Account;
import org.transactions.server.entity.Customer;
import org.transactions.server.entity.Transaction;
import org.transactions.server.exception.NotFoundException;
import org.transactions.server.repository.TransactionRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerService customerService;
    private final AccountService accountService;

    public Transaction get(UUID id) {
        return transactionRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Transaction with id " + id + " not found"));
    }

    public Transaction create(TransactionDto transaction) {
        Transaction newTransaction = new Transaction();
        newTransaction.setId(UUID.randomUUID());
        newTransaction.setInsertTime(OffsetDateTime.now());
        newTransaction.setAmount(transaction.getAmount());

        Customer customer = customerService.get(transaction.getCustomerId());
        newTransaction.setCustomer(customer);

        Account account = accountService.get(transaction.getAccountId());
        newTransaction.setAccount(account);
        newTransaction.setCurrency(account.getCurrency());

        account.setBalance(account.getBalance().add(newTransaction.getAmount()));
        accountService.save(account);

        return transactionRepository.save(newTransaction);
    }

    public Transaction update(UUID id, TransactionDto update) {
        Transaction existing = get(id);

        updateBalance(update, existing);

        existing.setAmount(update.getAmount());

        return transactionRepository.save(existing);
    }

    public void delete(UUID id) {
        Transaction transaction = get(id);

        Account account = transaction.getAccount();
        account.setBalance(account.getBalance().subtract(transaction.getAmount()));
        accountService.save(account);

        transactionRepository.deleteById(id);
    }

    private void updateBalance(TransactionDto update, Transaction existing) {
        BigDecimal currentAmount = existing.getAmount();
        BigDecimal diff = currentAmount.subtract(update.getAmount());
        Account account = existing.getAccount();
        account.setBalance(account.getBalance().add(diff));
        accountService.save(account);
    }

}
