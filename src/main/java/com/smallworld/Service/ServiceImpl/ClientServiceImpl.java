package com.smallworld.Service.ServiceImpl;

import com.smallworld.Model.Transaction;
import com.smallworld.Service.ClientService;
import com.smallworld.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final TransactionService transactionService;

    @Autowired
    public ClientServiceImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public long countUniqueClients() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        if (transactions.isEmpty()) {
            return 0;
        }

        return transactions.stream()
                .map(Transaction::getSenderFullName)
                .distinct()
                .count();
    }

    @Override
    public boolean hasOpenComplianceIssues(String clientFullName) {
        List<Transaction> transactions = transactionService.getAllTransactions();

        if (transactions.isEmpty()) {
            return false;
        }

        return transactions.stream()
                .filter(transaction -> transaction.getSenderFullName().equals(clientFullName) || transaction.getBeneficiaryFullName().equals(clientFullName))
                .anyMatch(transaction -> transaction.getIssueId() != null && !transaction.isIssueSolved());
    }

    @Override
    public Optional<String> getTopSender() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        if (transactions.isEmpty()) {
            return Optional.empty();
        }

        Map<String, Double> senderAmountMap = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getSenderFullName, Collectors.summingDouble(Transaction::getAmount)));

        return senderAmountMap.entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey);
    }
    @Override
    public Map<String, Transaction> getTransactionsByBeneficiaryName() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        if (transactions.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, Transaction> transactionMap = new HashMap<>();

        for (Transaction transaction : transactions) {
            String beneficiaryFullName = transaction.getBeneficiaryFullName();

            // Check if the beneficiary is already in the map
            if (transactionMap.containsKey(beneficiaryFullName)) {
             // ignoring the duplicate and picking only the first transaction
            } else {
                // If not a duplicate, simply add the transaction to the map
                transactionMap.put(beneficiaryFullName, transaction);
            }
        }
        return transactionMap;
    }
}
