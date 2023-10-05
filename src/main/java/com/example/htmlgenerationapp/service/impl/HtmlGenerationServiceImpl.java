package com.example.htmlgenerationapp.service.impl;

import com.example.htmlgenerationapp.service.HtmlGenerationService;
import java.util.ArrayList;
import java.util.List;
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
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            Future<String> future = executorService.submit(new HtmlGenerationTask(i));
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
        private final int taskId;

        public HtmlGenerationTask(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public String call() throws Exception {
            String generatedContent = "<p>This is generated content from thread "
                    + taskId + ".</p>";
            Thread.sleep(500);
            return generatedContent;
        }
    }
    //static List<String> futures = new ArrayList<>();
    //    @Override
    //    public String generateHtmlPage() {
    //        int numberOfThreads = 3;
    //        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
    //
    //        for (int i = 0; i < numberOfThreads; i++) {
    //            Future<String> future = executorService.submit(new HtmlGenerationTask());
    //        }
    //        executorService.shutdown();
    //
    //        return "<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'>
    //        <title>My HTML page"
    //                + "</title></head><body>" + futures.toString() + "</body></html>";
    //    }
    //
    //    private static class HtmlGenerationTask implements Callable<String> {
    //        @Override
    //        public String call() throws Exception {
    //            String generatedContent = "<p>This is generated content from thread "
    //                    + Thread.currentThread().getId() + ".</p>";
    //            futures.add(generatedContent);
    //            Thread.sleep(500);
    //            return generatedContent;
    //        }
    //    }
}
