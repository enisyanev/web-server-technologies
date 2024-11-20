package org.transactions.server.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.transactions.server.controller.dto.TransactionDetailsDto;
import org.transactions.server.entity.Transaction;
import org.transactions.server.entity.TransactionDetails;
import org.transactions.server.exception.NotFoundException;
import org.transactions.server.repository.TransactionDetailsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class TransactionDetailsService {

    private final TransactionDetailsRepository transactionDetailsRepository;
    private final TransactionService transactionService;

    public TransactionDetails get(UUID id) {
        return transactionDetailsRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Transaction details with id " + id + " not found"));
    }

    public TransactionDetails create(TransactionDetailsDto transactionDetails) {
        Transaction transaction = transactionService.get(transactionDetails.getTransactionId());

        TransactionDetails details = new TransactionDetails();
        details.setId(transaction.getId());
        details.setType(transactionDetails.getType());
        details.setMessage(transactionDetails.getMessage());
        details.setSource(transactionDetails.getSource());

        return transactionDetailsRepository.save(details);
    }

    public TransactionDetails update(UUID id, TransactionDetailsDto transactionDetails) {
        TransactionDetails existing = get(id);

        existing.setType(transactionDetails.getType());
        existing.setMessage(transactionDetails.getMessage());
        existing.setSource(transactionDetails.getSource());

        return transactionDetailsRepository.save(existing);
    }

    public void delete(UUID id) {
        transactionDetailsRepository.deleteById(id);
    }

}
