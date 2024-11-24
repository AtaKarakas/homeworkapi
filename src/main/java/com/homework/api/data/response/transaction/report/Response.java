package com.homework.api.data.response.transaction.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Response {

    private Integer count;
    private Integer total;
    private String currency;

}
