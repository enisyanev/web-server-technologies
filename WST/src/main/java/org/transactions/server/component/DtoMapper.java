package org.transactions.server.component;

import org.springframework.stereotype.Component;
import org.transactions.server.controller.dto.AccountResponse;
import org.transactions.server.controller.dto.CustomerResponse;
import org.transactions.server.controller.dto.TransactionDetailsResponse;
import org.transactions.server.controller.dto.TransactionResponse;
import org.transactions.server.entity.Account;
import org.transactions.server.entity.Customer;
import org.transactions.server.entity.Transaction;
import org.transactions.server.entity.TransactionDetails;

@Component
public class DtoMapper {

    public AccountResponse toResponse(Account account) {
        return new AccountResponse(account.getId(), account.getCurrency(), account.getBalance(), account.getCustomer().getId());
    }

    public CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(),
            customer.getAccounts().stream().map(this::toResponse).toList());
    }

    public TransactionResponse toResponse(Transaction transaction) {
        return new TransactionResponse(transaction.getId(), transaction.getCustomer().getId(), transaction.getAccount().getId(),
            transaction.getAmount(), transaction.getCurrency(), transaction.getInsertTime(),
            toResponse(transaction.getTransactionDetails()));
    }

    public TransactionDetailsResponse toResponse(TransactionDetails transactionDetails) {
        if (transactionDetails == null) {
            return null;
        }
        return new TransactionDetailsResponse(transactionDetails.getId(), transactionDetails.getType(),
            transactionDetails.getMessage(), transactionDetails.getSource());
    }
}
