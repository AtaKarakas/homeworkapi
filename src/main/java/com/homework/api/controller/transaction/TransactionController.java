package com.homework.api.controller.transaction;

import com.homework.api.data.request.GetTransactionRequest;
import com.homework.api.data.request.TransactionQueryRequest;
import com.homework.api.data.request.TransactionReportRequest;
import com.homework.api.data.response.transaction.get.GetTransactionResponse;
import com.homework.api.data.response.transaction.query.TransactionQueryResponse;
import com.homework.api.data.response.transaction.report.TransactionReportResponse;
import com.homework.api.service.transaction.TransactionService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3")
public class TransactionController {

    @Resource
    private TransactionService transactionService;

    @PostMapping("/transactions/report")
    public ResponseEntity<TransactionReportResponse> getTransactionReport(
            @RequestBody TransactionReportRequest transactionReportRequest,
            @RequestHeader("Authorization") String auth) {
        return transactionService.getTransactionReport(transactionReportRequest,auth);
    }


    @PostMapping("/transactions/list")
    public ResponseEntity<TransactionQueryResponse> getTransactionQuery(
            @RequestBody TransactionQueryRequest transactionQueryRequest,
            @RequestHeader("Authorization") String auth) {
        return transactionService.getTransactionQuery(transactionQueryRequest,auth);
    }

    @PostMapping("/transaction")
    public ResponseEntity<GetTransactionResponse> getTransaction(
            @RequestBody GetTransactionRequest transactionRequest,
            @RequestHeader("Authorization") String auth) {
        return transactionService.getTransaction(transactionRequest,auth);
    }
}
