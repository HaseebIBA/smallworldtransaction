package com.smallworld.Service;

import com.smallworld.Model.Transaction;

import java.util.List;

public interface TransactionService {
    void setTransactions(List<Transaction> transactions);

    List<Transaction> getAllTransactions();
}
