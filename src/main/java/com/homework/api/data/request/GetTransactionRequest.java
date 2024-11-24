package com.homework.api.data.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetTransactionRequest {

    @Size(max = 32 , message = "This field cannot exceed 32 characters.")
    private String transactionId;
}
