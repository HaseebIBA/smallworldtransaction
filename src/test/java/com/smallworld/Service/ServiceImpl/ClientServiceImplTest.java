package com.smallworld.Service.ServiceImpl;


import com.smallworld.Service.TransactionService;
import com.smallworld.Model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class ClientServiceImplTest {
    @Mock
    private TransactionService transactionService;

    private com.smallworld.Service.ServiceImpl.ClientServiceImpl clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clientService = new com.smallworld.Service.ServiceImpl.ClientServiceImpl(transactionService);
    }

    @Test
    @DisplayName("Count unique clients: No transactions - Returns zero")
    void countUniqueClients_NoTransactions_ReturnsZero() {
        when(transactionService.getAllTransactions()).thenReturn(new ArrayList<>());

        long uniqueClients = clientService.countUniqueClients();

        assertEquals(0, uniqueClients);
    }

    @Test
    @DisplayName("Count unique clients: Transactions with unique senders - Returns correct count")
    void countUniqueClients_TransactionsWithUniqueSenders_ReturnsCorrectCount() {
        List<Transaction> transactions = getAllTransactions();
        when(transactionService.getAllTransactions()).thenReturn(transactions);

        long uniqueClients = clientService.countUniqueClients();

        assertEquals(5, uniqueClients);
    }

    @Test
    @DisplayName("Check if client has open compliance issues: No transactions - Returns false")
    void hasOpenComplianceIssues_NoTransactions_ReturnsFalse() {
        when(transactionService.getAllTransactions()).thenReturn(getAllTransactions());

        boolean hasIssues = clientService.hasOpenComplianceIssues("Client");

        assertFalse(hasIssues);
    }

    @Test
    @DisplayName("Check if client has open compliance issues: Transactions without issues - Returns false")
    void hasOpenComplianceIssues_TransactionsWithoutIssues_ReturnsFalse() {
        List<Transaction> transactions = getAllTransactions();
        when(transactionService.getAllTransactions()).thenReturn(transactions);

        boolean hasIssues = clientService.hasOpenComplianceIssues("Client");

        assertFalse(hasIssues);
    }

    @Test
    @DisplayName("Check if client has open compliance issues: Transactions with open issues - Returns true")
    void hasOpenComplianceIssues_TransactionsWithOpenIssues_ReturnsTrue() {
        when(transactionService.getAllTransactions()).thenReturn(getAllTransactions());

        boolean hasIssues = clientService.hasOpenComplianceIssues("Tom Shelby");

        assertTrue(hasIssues);
    }

    @Test
    @DisplayName("Get top sender: No transactions - Returns Optional empty")
    void getTopSender_NoTransactions_ReturnsOptionalEmpty() {
        when(transactionService.getAllTransactions()).thenReturn(new ArrayList<>());

        Optional<String> topSender = clientService.getTopSender();

        assertTrue(topSender.isEmpty());
    }

    @Test
    @DisplayName("Get top sender: Transactions with single top sender - Returns correct sender")
    void getTopSender_TransactionsWithSingleTopSender_ReturnsCorrectSender() {
        List<Transaction> transactions = getAllTransactions();
        when(transactionService.getAllTransactions()).thenReturn(transactions);

        Optional<String> topSender = clientService.getTopSender();

        assertTrue(topSender.isPresent());
        assertEquals("Grace Burgess", topSender.get());
    }



    private List<Transaction> getAllTransactions() {
        return Arrays.asList(
                new Transaction(663458, 430.2, "Tom Shelby", 22, "Alfie Solomons", 33, 1, false, "Looks like money laundering"),
                new Transaction(1284564, 150.2, "Tom Shelby", 22, "Arthur Shelby", 60, 2, true, "Never gonna give you up"),
                new Transaction(1284564, 150.2, "Tom Shelby", 22, "Arthur Shelby", 60, 3, false, "Looks like money laundering"),
                new Transaction(96132456, 67.8, "Aunt Polly", 34, "Aberama Gold", 58, null, true, null),
                new Transaction(5465465, 985.0, "Arthur Shelby", 60, "Ben Younger", 47, 15, false, "Something's fishy"),
                new Transaction(1651665, 97.66, "Tom Shelby", 22, "Oswald Mosley", 37, 65, true, "Never gonna let you down"),
                new Transaction(6516461, 33.22, "Aunt Polly", 34, "MacTavern", 30, null, true, null),
                new Transaction(32612651, 666.0, "Grace Burgess", 31, "Michael Gray", 58, 54, false, "Something ain't right"),
                new Transaction(32612651, 666.0, "Grace Burgess", 31, "Michael Gray", 58, 78, true, "Never gonna run around and desert you"),
                new Transaction(32612651, 666.0, "Grace Burgess", 31, "Michael Gray", 58, 99, false, "Don't let this transaction happen"),
                new Transaction(36448252, 154.15, "Billy Kimber", 58, "Winston Churchill", 48, null, true, null),
                new Transaction(645645111, 215.17, "Billy Kimber", 58, "Major Campbell", 41, null, true, null),
                new Transaction(45431585, 89.77, "Billy Kimber", 58, "Luca Changretta", 46, null, true, null)
        );
    }
}
