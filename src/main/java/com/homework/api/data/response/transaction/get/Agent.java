package com.homework.api.data.response.transaction.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Agent {

    private Integer id;
    private String customerIp;
    private String customerUserAgent;
    private String merchantIp;
}
