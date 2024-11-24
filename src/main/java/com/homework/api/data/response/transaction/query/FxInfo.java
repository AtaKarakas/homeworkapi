package com.homework.api.data.response.transaction.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FxInfo {

    private Double originalAmount;
    private String originalCurrency;

}
