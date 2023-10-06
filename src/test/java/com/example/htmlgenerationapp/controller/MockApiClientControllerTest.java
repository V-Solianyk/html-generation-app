package com.example.htmlgenerationapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.htmlgenerationapp.service.MockApiClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

@WebMvcTest(MockApiClientController.class)
public class MockApiClientControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private MockApiClientService mockApiClientService;

    @Test
    public void getThirdPartyData_ok() throws Exception {
        Mockito.when(mockApiClientService.fetchDataFromExternalApi())
                .thenReturn("Response from third part api");
        mvc.perform(get("/external-data")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Response from third part api"));
    }

    @Test
    public void getThirdPartyData_apiDoesntRespond_notOk() throws Exception {
        Mockito.when(mockApiClientService.fetchDataFromExternalApi())
                .thenThrow(HttpClientErrorException.class);
        mvc.perform(get("/external-data")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isServiceUnavailable())
                .andExpect(content().json("{\"errorMessage\":\"The service is temporarily "
                        + "unavailable, try again later\"}"));
    }
}
