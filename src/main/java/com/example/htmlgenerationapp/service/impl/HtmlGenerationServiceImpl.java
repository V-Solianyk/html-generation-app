package com.example.htmlgenerationapp.service.impl;

import com.example.htmlgenerationapp.service.HtmlGenerationService;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.springframework.stereotype.Service;

@Service
public class HtmlGenerationServiceImpl implements HtmlGenerationService {
    private static final String HTML_TEMPLATE_START = "<!DOCTYPE html><html lang='en'><head>" +
            "<meta charset='UTF-8'><title>My HTML page</title></head><body>";
    private static final String HTML_TEMPLATE_END = "</body></html>";
    private static final String GENERATED_CONTENT_START = "<p>This is generated content from thread ";
    private static final String GENERATED_CONTENT_END = ".</p>";
    private static final int GENERATION_DELAY_MILLISECONDS = 500;

    @Override
    public String generateHtmlPage() {
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Set<Future<String>> futures = new HashSet<>();
        for (int i = 0; i < numberOfThreads; i++) {
            Future<String> future = executorService.submit(new HtmlGenerationTask());
            futures.add(future);
        }
        executorService.shutdown();
        StringBuilder htmlContent = new StringBuilder();
        for (Future<String> future : futures) {
            try {
                htmlContent.append(future.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Error while generating HTML content", e);
            }
        }
        return HTML_TEMPLATE_START + htmlContent + HTML_TEMPLATE_END;
    }

    private static class HtmlGenerationTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            String generatedContent = GENERATED_CONTENT_START + Thread.currentThread().getName()
                    + GENERATED_CONTENT_END;
            Thread.sleep(GENERATION_DELAY_MILLISECONDS);
            return generatedContent;
        }
    }
}
