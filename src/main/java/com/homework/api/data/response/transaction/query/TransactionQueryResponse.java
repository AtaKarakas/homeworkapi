package com.homework.api.data.response.transaction.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionQueryResponse {

    private Integer perPage;
    private Integer currentPage;
    private String nextPageUrl;
    private String prevPageUrl;
    private Integer from;
    private Integer to;
    private TransactionDataResponse data;
}
