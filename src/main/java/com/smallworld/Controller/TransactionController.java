package com.smallworld.Controller;

import com.smallworld.Miscellaneous.API_URI;
import com.smallworld.Miscellaneous.Error_Message;
import com.smallworld.Model.Transaction;
import com.smallworld.Service.ClientService;
import com.smallworld.Service.TransactionAmountService;
import com.smallworld.Exception.TransactionLoaderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(API_URI.TRANSACTION)
public class TransactionController {

    @Autowired
    private TransactionAmountService transactionAmountService;
    @Autowired
    private ClientService clientService;
    /**
     * Returns the sum of the amounts of all transactions
     */
   @GetMapping(API_URI.TOTALTRANSACTIONAMOUNT)
    public ResponseEntity<List<Transaction>>getTotalTransactionAmount() {

        List<Transaction> transactionList = transactionAmountService.getAllTransactions();
        if (transactionList.isEmpty())
        {
            throw new TransactionLoaderException(Error_Message.TRANSACTION_NOT_FOUND);
        }
        return ResponseEntity.ok(transactionList);
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    @GetMapping(API_URI.TOTALTRANSACTIONAMOUNTSENTBY)
    public ResponseEntity<Double> getTotalTransactionAmountSentBy(@RequestParam(name = "senderFullName") String senderFullName) {

        double totalTransactionAmount = transactionAmountService. getTotalTransactionAmountSentBy(senderFullName);
        if (totalTransactionAmount == 0)
        {
            throw new TransactionLoaderException(Error_Message.TRANSACTION_NOT_FOUND);
        }
        return ResponseEntity.ok(totalTransactionAmount);

    }

    /**     * Returns the highest transaction amount
     */
    @GetMapping(API_URI.MAXTRANSACTIONAMOUNT)
    public ResponseEntity<Double> getMaxTransactionAmount() {

        double maxTransactionAmount = transactionAmountService.getMaxTransactionAmount();
        if (maxTransactionAmount == 0) {
            throw new TransactionLoaderException(Error_Message.TRANSACTION_NOT_FOUND);
        }
        return ResponseEntity.ok(maxTransactionAmount);
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    @GetMapping(API_URI.COUNTUNIQUECLIENTS)
    public ResponseEntity<Long> countUniqueClients(){

        long uniqueClients = clientService.countUniqueClients();
        if (uniqueClients == 0) {
            throw new TransactionLoaderException(Error_Message.TRANSACTION_NOT_FOUND);
        }
        return ResponseEntity.ok(uniqueClients);
    }


    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    @GetMapping(API_URI.HASOPENCOMPLIANCEISSUES)
    public ResponseEntity<Boolean> hasOpenComplianceIssues(@RequestParam(name = "clientFullName") String clientFullName) {

        boolean hasOpenComplianceIssues = clientService.hasOpenComplianceIssues(clientFullName);
        if (!hasOpenComplianceIssues) {
            throw new TransactionLoaderException(Error_Message.TRANSACTION_NOT_FOUND);
        }
        return ResponseEntity.ok(hasOpenComplianceIssues);
    }


    /**
     * Returns all transactions indexed by beneficiary name
     */
    @GetMapping(API_URI.TRANSACTIONSBYBENEFICIARYNAME)
    public ResponseEntity<Map<String, Transaction>> getTransactionsByBeneficiaryName() {

        Map<String, Transaction> transactionsByBeneficiaryName = clientService.getTransactionsByBeneficiaryName();
        if (transactionsByBeneficiaryName == null) {
            throw new TransactionLoaderException(Error_Message.TRANSACTION_NOT_FOUND);
        }
        return ResponseEntity.ok(transactionsByBeneficiaryName);
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    @GetMapping(API_URI.UNSOLVEDISSUEIDS)
    public ResponseEntity<Set<Integer>> getUnsolvedIssueIds() {

        Set<Integer> unsolvedIssueIds = transactionAmountService.getUnsolvedIssueIds();
        if (unsolvedIssueIds == null) {
            throw new TransactionLoaderException(Error_Message.TRANSACTION_NOT_FOUND);
        }
        return ResponseEntity.ok(unsolvedIssueIds);
    }
    /**
     * Returns a list of all solved issue messages
     */
    @GetMapping(API_URI.ALLSOLVEDISSUEMESSAGES)
    public ResponseEntity<List<String>> getAllSolvedIssueMessages() {

        List<String> allSolvedIssueMsg = transactionAmountService.getAllSolvedIssueMessages();
        if (allSolvedIssueMsg == null) {
            throw new TransactionLoaderException(Error_Message.TRANSACTION_NOT_FOUND);
        }
        return ResponseEntity.ok(allSolvedIssueMsg);
    }

    /**
     * Returns the 3 transactions with highest amount sorted by amount descending
     */
    @GetMapping(API_URI.TOP3TRANSACTIONBYAMOUNT)
    public ResponseEntity<List<Transaction>> getTop3TransactionsByAmount() {

        List<Transaction> top3TransactionsByAmount = transactionAmountService.getTop3TransactionsByAmount();
        if (top3TransactionsByAmount == null) {
            throw new TransactionLoaderException(Error_Message.TRANSACTION_NOT_FOUND);
        }
        return ResponseEntity.ok(top3TransactionsByAmount);
    }

    /**
     * Returns the sender with the most total sent amount
     */
    @GetMapping(API_URI.TOPSENDER)
    public ResponseEntity<Optional<String>> getTopSender() {

        Optional<String> topSender = clientService.getTopSender();
        if (topSender.isEmpty()) {
            throw new TransactionLoaderException(Error_Message.TRANSACTION_NOT_FOUND);
        }
        return ResponseEntity.ok(topSender);
    }
}
