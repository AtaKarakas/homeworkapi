package com.homework.api;

import com.homework.api.data.request.GetTransactionRequest;
import com.homework.api.data.request.LoginRequest;
import com.homework.api.data.request.TransactionQueryRequest;
import com.homework.api.data.request.TransactionReportRequest;
import com.homework.api.data.response.Status;
import com.homework.api.data.response.login.LoginResponse;
import com.homework.api.data.response.transaction.get.GetTransactionResponse;
import com.homework.api.data.response.transaction.query.CustomerInfo;
import com.homework.api.data.response.transaction.query.TransactionQueryResponse;
import com.homework.api.data.response.transaction.report.TransactionReportResponse;
import com.homework.api.exception.CustomException;
import com.homework.api.service.client.ClientService;
import com.homework.api.service.login.LoginService;
import com.homework.api.service.request.RequestService;
import com.homework.api.service.transaction.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceTests {

    @InjectMocks
    private LoginService loginService;

    @InjectMocks
    private ClientService clientService;

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private RequestService requestService;

    @Mock
    private Logger LOG;

    private HttpHeaders headers;

    @BeforeEach
    void setUp() {
        headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer token");
    }

    @Test
    void testGetCustomerInfo_Success() {

        GetTransactionRequest request = new GetTransactionRequest();
        CustomerInfo mockResponse = new CustomerInfo();
        when(requestService.generateDefaultHeader()).thenReturn(headers);
        when(requestService.postRequest(anyString(), any(HttpEntity.class), eq(CustomerInfo.class)))
                .thenReturn(ResponseEntity.ok(mockResponse));

        // Act
        ResponseEntity<CustomerInfo> response = clientService.getCustomerInfo(request, "Bearer token");

        // Assert
        assertNotNull(response.getBody());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    void testLogin_Success() {
        // Arrange
        LoginRequest request = new LoginRequest();
        LoginResponse mockResponse = new LoginResponse();
        mockResponse.setToken("valid-token");
        mockResponse.setStatus(Status.APPROVED);

        when(requestService.generateDefaultHeader()).thenReturn(headers);
        when(requestService.postRequest(anyString(), any(HttpEntity.class), eq(LoginResponse.class)))
                .thenReturn(ResponseEntity.ok(mockResponse));

        // Act
        ResponseEntity<LoginResponse> response = loginService.login(request);

        // Assert
        assertNotNull(response.getBody());
        assertEquals("valid-token", response.getBody().getToken());
    }

    @Test
    void testGetTransactionReport_InvalidStatus() {

        TransactionReportRequest request = new TransactionReportRequest();
        TransactionReportResponse invalidResponse = new TransactionReportResponse();
        invalidResponse.setStatus(Status.valueOf("DECLINED"));

        when(requestService.generateDefaultHeader()).thenReturn(headers);
        when(requestService.postRequest(anyString(), any(HttpEntity.class), eq(TransactionReportResponse.class)))
                .thenReturn(ResponseEntity.ok(invalidResponse));

        // Act & Assert
        HttpClientErrorException exception = assertThrows(
                HttpClientErrorException.class,
                () -> transactionService.getTransactionReport(request, "Bearer token")
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testGetTransactionQuery_NullResponse() {
        // Arrange
        TransactionQueryRequest request = new TransactionQueryRequest();

        when(requestService.generateDefaultHeader()).thenReturn(headers);
        when(requestService.postRequest(anyString(), any(HttpEntity.class), eq(TransactionQueryResponse.class)))
                .thenReturn(ResponseEntity.ok(null));

        // Act & Assert
        HttpClientErrorException exception = assertThrows(
                HttpClientErrorException.class,
                () -> transactionService.getTransactionQuery(request, "Bearer token")
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testGetTransaction_UnexpectedError() {
        // Arrange
        GetTransactionRequest request = new GetTransactionRequest();
        RestClientException restClientException = new RestClientException("Timeout");

        when(requestService.generateDefaultHeader()).thenReturn(headers);
        when(requestService.postRequest(anyString(), any(HttpEntity.class), eq(GetTransactionResponse.class)))
                .thenThrow(restClientException);

        // Act & Assert
        CustomException exception = assertThrows(
                CustomException.class,
                () -> transactionService.getTransaction(request, "Bearer token")
        );

        assertEquals("Unexpected error while processing transaction query", exception.getMessage());
    }
}