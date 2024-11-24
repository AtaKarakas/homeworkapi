package com.homework.api.data.response.transaction.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDataResponse {

    private Fx fx;
    private CustomerInfo customerInfo;
    private Merchant merchant;
    private Ipn ipn;
    private TransactionData transaction;
    private Acquirer acquirer;
    private boolean refundable;

}
