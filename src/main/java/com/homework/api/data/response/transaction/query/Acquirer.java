package com.homework.api.data.response.transaction.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Acquirer {

    private Integer id;
    private String name;
    private String code;
    private String type;
}
