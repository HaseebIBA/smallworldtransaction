package com.smallworld.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private int mtn;
    private double amount;
    private String senderFullName;
    private int senderAge;
    private String beneficiaryFullName;
    private int beneficiaryAge;
    private Integer issueId;
    private boolean issueSolved;
    private String issueMessage;

}
