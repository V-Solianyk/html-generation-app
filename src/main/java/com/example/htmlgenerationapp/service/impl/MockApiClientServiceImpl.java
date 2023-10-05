package com.example.htmlgenerationapp.service.impl;

import com.example.htmlgenerationapp.retry.CustomRetryable;
import com.example.htmlgenerationapp.service.MockApiClientService;
import java.util.Random;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class MockApiClientServiceImpl implements MockApiClientService {
    private final Random random = new Random();

   @CustomRetryable(maxAttempts = 4, initialDelay = 1500L)
    public String fetchDataFromExternalApi() {
        boolean rateLimitExceeded = random.nextInt(10) < 8;
        if (rateLimitExceeded) {
        System.out.println("The third-party api is not responding");
        throw new HttpClientErrorException(HttpStatus.TOO_MANY_REQUESTS);
    }
        return "Response from third-party API";
    }
}
