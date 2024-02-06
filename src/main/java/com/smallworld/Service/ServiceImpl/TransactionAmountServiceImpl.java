package com.smallworld.Service.ServiceImpl;


import com.smallworld.Model.Transaction;
import com.smallworld.Service.TransactionAmountService;
import com.smallworld.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TransactionAmountServiceImpl implements TransactionAmountService {

    @Autowired
    private TransactionService transactionService;

    @Override
    public double getTotalTransactionAmount() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        if (transactions.isEmpty()) {
            return 0;
        }

        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        List<Transaction> transactions = transactionService.getAllTransactions();

        if (transactions.isEmpty()) {
            return 0;
        }

        return transactions.stream()
                .filter(transaction -> transaction.getSenderFullName().equals(senderFullName))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public double getMaxTransactionAmount() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        if (transactions.isEmpty()) {
            return 0;
        }

        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .max()
                .orElse(0);
    }

    @Override
    public List<Transaction> getTop3TransactionsByAmount() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        return transactions.stream()
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    @Override
    public Set<Integer> getUnsolvedIssueIds() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        if (transactions.isEmpty()) {
            return Collections.emptySet();
        }

        return transactions.stream()
                .filter(transaction -> transaction.getIssueId() != null && !transaction.isIssueSolved())
                .map(Transaction::getIssueId)
                .collect(Collectors.toSet());
    }

    @Override
    public List<String> getAllSolvedIssueMessages() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        if (transactions.isEmpty()) {
            return Collections.emptyList();
        }

        return transactions.stream()
                .filter(Transaction::isIssueSolved)
                .map(Transaction::getIssueMessage)
                .toList();
    }
    @Override
    public List<Transaction> getAllTransactions() {
       return transactionService.getAllTransactions();
    }
}
