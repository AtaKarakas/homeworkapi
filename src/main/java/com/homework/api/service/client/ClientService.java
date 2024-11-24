package com.homework.api.service.client;

import com.homework.api.data.request.GetTransactionRequest;
import com.homework.api.data.response.transaction.query.CustomerInfo;
import com.homework.api.exception.CustomException;
import com.homework.api.message.ErrorMessages;
import com.homework.api.service.request.RequestService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

@Service(value = "clientService")
public class ClientService {

    private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);

    @Value(("${financial.house.getclient.url}"))
    private String getTransactionUrl;

    @Resource
    private RequestService requestService;

    public ResponseEntity<CustomerInfo> getCustomerInfo(GetTransactionRequest transactionRequest, String auth) {
        HttpHeaders headers = requestService.generateDefaultHeader();
        headers.add(HttpHeaders.AUTHORIZATION, auth);
        HttpEntity<GetTransactionRequest> httpEntity = new HttpEntity<>(transactionRequest, headers);

        try{
            ResponseEntity<CustomerInfo> responseEntity = requestService.postRequest(getTransactionUrl,httpEntity, CustomerInfo.class);

            if(responseEntity.getBody() == null) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, ErrorMessages.TRANSACTION_ERROR);
            }
            return responseEntity;
        }catch (HttpServerErrorException ex) {
            String responseBody = ex.getResponseBodyAsString();
            LOG.error(responseBody, ex);

            throw new CustomException(responseBody, ex);
        } catch (RestClientException ex) {
            LOG.error("RestTemplate error: {}", ex.getMessage(), ex);
            throw new CustomException("Unexpected error while processing transaction query", ex);
        }
    }

}
