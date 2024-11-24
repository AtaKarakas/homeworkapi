package com.homework.api.data.response.transaction.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionMerchant {

    private String referenceNo;
    private String status;
    private String operation;
    private String message;
    private String createdAt;
    private String transactionId;

}
