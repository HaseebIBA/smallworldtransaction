package com.smallworld.Service;

import com.smallworld.Model.Transaction;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface TransactionAmountService {
    double getTotalTransactionAmount();

    double getTotalTransactionAmountSentBy(String senderFullName);

    double getMaxTransactionAmount();

    List<Transaction> getTop3TransactionsByAmount();

    List<Transaction>  getAllTransactions();

    Set<Integer> getUnsolvedIssueIds() ;

    List<String> getAllSolvedIssueMessages();
}
