package com.smallworld.Miscellaneous;


import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class API_URI {
    public static final String TRANSACTION = "/transaction";
    public static final String TOTALTRANSACTIONAMOUNT = "/totalTransactionAmount";
    public static final String TOTALTRANSACTIONAMOUNTSENTBY = "/getTotalTransactionAmountSentBy";
    public static final String MAXTRANSACTIONAMOUNT = "/getMaxTransactionAmount";
    public static final String COUNTUNIQUECLIENTS = "/countUniqueClients";
    public static final String HASOPENCOMPLIANCEISSUES = "/hasOpenComplianceIssues";
    public static final String TRANSACTIONSBYBENEFICIARYNAME = "/getTransactionsByBeneficiaryName";

    public static final String UNSOLVEDISSUEIDS = "/getUnsolvedIssueIds";

    public static final String ALLSOLVEDISSUEMESSAGES= "/getAllSolvedIssueMessages";

    public static final String TOP3TRANSACTIONBYAMOUNT= "/getTop3TransactionsByAmount";

    public static final String TOPSENDER= "/getTopSender";

}
