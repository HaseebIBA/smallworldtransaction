package com.smallworld.Service.ServiceImpl;

import com.smallworld.Model.Transaction;
import com.smallworld.Service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private List<Transaction> transactions;

    public TransactionServiceImpl() {
        this.transactions = new ArrayList<>();
    }


    @Override
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }


    @Override
    public List<Transaction> getAllTransactions() {

        return transactions;
    }
}
