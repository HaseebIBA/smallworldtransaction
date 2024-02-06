package com.smallworld.Service;

import com.smallworld.Model.Transaction;

import java.util.Map;
import java.util.Optional;

public interface ClientService {
    long countUniqueClients();

    boolean hasOpenComplianceIssues(String clientFullName);

    Map<String, Transaction> getTransactionsByBeneficiaryName();

    Optional<String> getTopSender();
}
