package com.example.htmlgenerationapp.controller;

import com.example.htmlgenerationapp.service.MockApiClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MockApiClientController {
    private final MockApiClientService mockApiClientService;

    @GetMapping("/external-data")
    public ResponseEntity<String> getThirdPartyData() {
        return ResponseEntity.ok(mockApiClientService.fetchDataFromExternalApi());
    }
}
