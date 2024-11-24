package com.homework.api.data.response.transaction.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MerchantInfo {

    private String referenceNo;
    private Integer merchantId;
    private String status;
    private String channel;
    private String customData;
    private String chainId;
    private Integer agentInfoId;
    private String operation;
    private Integer fxTransactionId;
    private String updatedAt;
    private String createdAt;
    private Integer id;
    private Integer acquirerTransactionId;
    private String code;
    private String transactionId;
    private Agent agent;

}
