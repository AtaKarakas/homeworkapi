package com.homework.api.data.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionReportRequest {

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date toDate;

    private int merchant;
    private int acquirer;

}
