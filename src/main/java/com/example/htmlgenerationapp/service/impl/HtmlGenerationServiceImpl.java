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
        return "<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><title>My HTML page"
                + "</title></head><body>" + htmlContent + "</body></html>";
    }

    private static class HtmlGenerationTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            String generatedContent = "<p>This is generated content from thread "
                    + Thread.currentThread().getName() + ".</p>";
            Thread.sleep(500);
            return generatedContent;
        }
    }
}
