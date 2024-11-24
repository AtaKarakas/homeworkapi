package com.homework.api.service.login;

import com.homework.api.data.request.LoginRequest;
import com.homework.api.data.response.login.LoginResponse;
import com.homework.api.data.response.Status;
import com.homework.api.exception.CustomException;
import com.homework.api.message.ErrorMessages;
import com.homework.api.service.request.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

@Service(value = "loginService")
public class LoginService {
    private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);

    @Value(("${financial.house.login.url}"))
    private String loginUrl;

    @Autowired
    private RequestService requestService;

    /**
     * Authenticates a user and retrieves login response with a token if successful.
     *
     * @param loginRequest the login request containing credentials
     * @return ResponseEntity containing {@link LoginResponse} if login is successful
     * @throws HttpClientErrorException if the response is null or token/status is invalid
     * @throws CustomException if a server error or unexpected error occurs
     */
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        HttpHeaders headers = requestService.generateDefaultHeader();
        HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest, headers);

        try{
            ResponseEntity<LoginResponse> loginResponse = requestService.postRequest(loginUrl, request, LoginResponse.class);
            if(loginResponse.getBody() == null || loginResponse.getBody().getToken() == null || !loginResponse.getBody().getStatus().equals(Status.APPROVED)){
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, ErrorMessages.LOGIN_ERROR);
            }
            return loginResponse;
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
