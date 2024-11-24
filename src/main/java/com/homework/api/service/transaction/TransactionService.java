package com.homework.api.service.transaction;

import com.homework.api.data.request.GetTransactionRequest;
import com.homework.api.data.request.TransactionQueryRequest;
import com.homework.api.data.request.TransactionReportRequest;
import com.homework.api.data.response.Status;
import com.homework.api.data.response.transaction.get.GetTransactionResponse;
import com.homework.api.data.response.transaction.query.TransactionQueryResponse;
import com.homework.api.data.response.transaction.report.TransactionReportResponse;
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

@Service
public class TransactionService {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionService.class);

    @Value(("${financial.house.transactionreport.url}"))
    private String transactionReportUrl;

    @Value(("${financial.house.transactionlist.url}"))
    private String transactionListUrl;

    @Value(("${financial.house.gettransaction.url}"))
    private String getTransactionUrl;

    @Resource
    private RequestService requestService;

    /**
     * Retrieves a single transaction based on the provided request and authorization token.
     *
     * @param transactionReportRequest the request containing transaction details
     * @param auth the authorization token
     * @return ResponseEntity containing {@link GetTransactionResponse} if successful
     * @throws HttpClientErrorException if the response is null
     * @throws CustomException if a server error or unexpected error occurs
     */
    public ResponseEntity<TransactionReportResponse> getTransactionReport(TransactionReportRequest transactionReportRequest,String auth) {
        HttpHeaders headers = requestService.generateDefaultHeader();
        headers.add("Authorization", auth);
        HttpEntity<TransactionReportRequest> httpEntity = new HttpEntity<>(transactionReportRequest, headers);

        try{
            ResponseEntity<TransactionReportResponse> responseEntity = requestService.postRequest(transactionReportUrl,httpEntity, TransactionReportResponse.class);

            if(responseEntity.getBody() == null || !(responseEntity.getBody().getStatus().equals(Status.APPROVED.getName()))) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, ErrorMessages.TRANSACTION_ERROR);
            }

            return responseEntity;
        } catch (HttpServerErrorException ex) {
            String responseBody = ex.getResponseBodyAsString();
            LOG.error(responseBody, ex);

            throw new CustomException(responseBody, ex);
        } catch (RestClientException ex) {
            LOG.error("RestTemplate error: {}", ex.getMessage(), ex);
            throw new CustomException("Unexpected error while processing transaction query", ex);
        }
    }

    /**
     * Queries transactions based on the provided request and authorization token.
     *
     * @param transactionQueryRequest the request containing query parameters
     * @param auth the authorization token
     * @return ResponseEntity containing {@link TransactionQueryResponse} if successful
     * @throws HttpClientErrorException if the response is null
     * @throws CustomException if a server error or unexpected error occurs
     */
    public ResponseEntity<TransactionQueryResponse> getTransactionQuery(TransactionQueryRequest transactionQueryRequest,String auth) {
        HttpHeaders headers = requestService.generateDefaultHeader();
        headers.add("Authorization", auth);
        HttpEntity<TransactionQueryRequest> httpEntity = new HttpEntity<>(transactionQueryRequest, headers);

        try{
            ResponseEntity<TransactionQueryResponse> responseEntity = requestService.postRequest(transactionListUrl,httpEntity, TransactionQueryResponse.class);

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

    /**
     * Retrieves a single transaction based on the provided request and authorization token.
     *
     * @param transactionRequest the request containing transaction details
     * @param auth the authorization token
     * @return ResponseEntity containing {@link GetTransactionResponse} if successful
     * @throws HttpClientErrorException if the response is null
     * @throws CustomException if a server error or unexpected error occurs
     */
    public ResponseEntity<GetTransactionResponse> getTransaction(GetTransactionRequest transactionRequest, String auth) {
        HttpHeaders headers = requestService.generateDefaultHeader();
        headers.add("Authorization", auth);
        HttpEntity<GetTransactionRequest> httpEntity = new HttpEntity<>(transactionRequest, headers);

        try{
            ResponseEntity<GetTransactionResponse> responseEntity = requestService.postRequest(getTransactionUrl,httpEntity, GetTransactionResponse.class);

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
