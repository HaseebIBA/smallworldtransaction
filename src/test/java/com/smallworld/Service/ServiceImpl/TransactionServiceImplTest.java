package com.smallworld.Service.ServiceImpl;
import com.smallworld.Service.TransactionService;
import com.smallworld.Model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionServiceImplTest {


    @InjectMocks
    private com.smallworld.Service.ServiceImpl.TransactionServiceImpl transactionServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionServiceImpl = new com.smallworld.Service.ServiceImpl.TransactionServiceImpl();
    }

    @Test
    @DisplayName("Setting Transactions: sets the transactions")
    void setTransactions_ShouldSetTransactions() {
        List<Transaction> transactions = getAllTransactions();

        transactionServiceImpl.setTransactions(transactions);

        assertEquals(transactions, transactionServiceImpl.getAllTransactions());
    }

    @Test
    @DisplayName("Get All Transactions: returns an empty list when no transactions are set")
    void getAllTransactions_ShouldReturnEmptyList_WhenNoTransactionsSet() {
        List<Transaction> transactions = transactionServiceImpl.getAllTransactions();

        assertEquals(0, transactions.size());
    }

    private List<Transaction> getAllTransactions() {
        return new ArrayList<>(List.of(
                new Transaction(
                        1284564,
                        150.2,
                        "Tom Shelby",
                        22,
                        "Arthur Shelby",
                        60,
                        3,
                        false,
                        "Looks like money laundering"
                ),
                new Transaction(
                        1284565,
                        150.2,
                        "Tom Shelby",
                        22,
                        "Arthur Shelby",
                        60,
                        3,
                        false,
                        "Looks like money laundering"
                ),
                new Transaction(
                        1284566,
                        150.2,
                        "Tom Shelby",
                        22,
                        "Arthur Shelby",
                        60,
                        3,
                        false,
                        "Looks like money laundering"
                )
        ));
    }
}
