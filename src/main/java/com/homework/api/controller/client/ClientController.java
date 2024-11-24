package com.homework.api.controller.client;

import com.homework.api.data.request.GetTransactionRequest;
import com.homework.api.data.response.transaction.query.CustomerInfo;
import com.homework.api.service.client.ClientService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3")
public class ClientController {

    @Resource
    private ClientService clientService;

    @PostMapping("/client")
    public ResponseEntity<CustomerInfo> getClientInfo(
            @RequestBody GetTransactionRequest getTransactionRequest,
            @RequestHeader("Authorization") String auth){
        return clientService.getCustomerInfo(getTransactionRequest, auth);
    }

}
