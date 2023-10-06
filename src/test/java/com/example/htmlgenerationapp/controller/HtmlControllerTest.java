package com.example.htmlgenerationapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.htmlgenerationapp.service.HtmlGenerationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HtmlController.class)
public class HtmlControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private HtmlGenerationService htmlService;

    @Test
    public void generateHtml_ok() throws Exception {
        Mockito.when(htmlService.generateHtmlPage())
                .thenReturn("<html><body>Generate content</body></html>");
        mvc.perform(get("/generate")
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("<html><body>Generate content</body></html>"));
    }
}
