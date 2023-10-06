package com.example.htmlgenerationapp.service.impl;

import com.example.htmlgenerationapp.retry.CustomRetryable;
import com.example.htmlgenerationapp.service.MockApiClientService;
import java.util.Random;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class MockApiClientServiceImpl implements MockApiClientService {
    private static final int MAX_RETRY_ATTEMPTS = 2;
    private static final long INITIAL_DELAY_MILLISECONDS = 1100L;
    private static final int MAX_RANDOM_VALUE = 10;
    private static final int SPEED_LIMIT_THRESHOLD = 5;
    private final Random random = new Random();

    @CustomRetryable(maxAttempts = MAX_RETRY_ATTEMPTS, initialDelay = INITIAL_DELAY_MILLISECONDS)
    public String fetchDataFromExternalApi() {
        boolean rateLimitExceeded = random.nextInt(MAX_RANDOM_VALUE) < SPEED_LIMIT_THRESHOLD;
        if (rateLimitExceeded) {
            System.out.println("The third-party api is not responding");
            throw new HttpClientErrorException(HttpStatus.TOO_MANY_REQUESTS);
        }
        return "Response from third-party API";
    }
}
