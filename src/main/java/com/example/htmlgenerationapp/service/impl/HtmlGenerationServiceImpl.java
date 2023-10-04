package com.example.htmlgenerationapp.service.impl;

import com.example.htmlgenerationapp.service.HtmlGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class HtmlGenerationServiceImpl implements HtmlGenerationService {
    private final TemplateEngine templateEngine;

    public String generateHtml() {
        Context context = new Context();
        context.setVariable("pageTitle", "The new page title");
        context.setVariable("pageContent", "The new text in the page.");

        String generatedHtml = templateEngine.process("template", context);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return generatedHtml;
    }
}
