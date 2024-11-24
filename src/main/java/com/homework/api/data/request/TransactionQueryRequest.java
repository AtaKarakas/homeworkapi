package com.homework.api.data.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionQueryRequest {

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date toDate;

    @Size(max = 64, message = "This field cannot exceed 64 characters.")
    private String status;

    @Size(max = 64, message = "This field cannot exceed 64 characters.")
    private String operation;

    private int merchantId;
    private int acquirerId;

    @Size(max = 32, message = "This field cannot exceed 32 characters.")
    private String paymentMethod;

    @Size(max = 256, message = "This field cannot exceed 256 characters.")
    private String errorCode;

    @Size(max = 128, message = "This field cannot exceed 128 characters.")
    private String filterField;

    @Size(max = 256, message = "This field cannot exceed 256 characters.")
    private String filterValue;

    private Integer page;
}
