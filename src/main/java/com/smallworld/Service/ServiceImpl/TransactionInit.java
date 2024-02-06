package com.smallworld.Service.ServiceImpl;

import com.smallworld.Service.TransactionService;
import com.smallworld.Util.TransactionLoader;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionInit {

    private final TransactionService transactionService;
    private final TransactionLoader transactionLoader;

    @PostConstruct
    public void TransactionInit() {

        log.info("Fetching transactions from file...");
        transactionService.setTransactions(transactionLoader.loadTransactions());
        log.info("Transaction fetched successfull");

    }
}
