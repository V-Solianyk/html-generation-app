package com.example.htmlgenerationapp.controller;

import com.example.htmlgenerationapp.service.HtmlGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HtmlController {
    private final HtmlGenerationService htmlService;

    @GetMapping(value = "/generate", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> generateHtml() {
        return ResponseEntity.ok(htmlService.generateHtmlPage());
    }
}
