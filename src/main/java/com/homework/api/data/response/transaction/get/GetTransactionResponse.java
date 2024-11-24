package com.homework.api.data.response.transaction.get;

import com.homework.api.data.response.transaction.query.CustomerInfo;
import com.homework.api.data.response.transaction.query.Fx;
import com.homework.api.data.response.transaction.query.Merchant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetTransactionResponse {

    private Fx fx;
    private CustomerInfo customerInfo;
    private Merchant merchant;
    private GetTransactionInfo transaction;

}
