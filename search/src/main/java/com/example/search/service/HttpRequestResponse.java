package com.example.search.service;

import com.example.search.config.EndpointConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class HttpRequestResponse {
    private final Map<String, HttpRequestResponse> requests = new HashMap<>();

    private final HttpServletRequest request;
    private final CompletableFuture<HttpServletResponse> responseSupplier;

    public HttpRequestResponse(HttpServletRequest request, CompletableFuture<HttpServletResponse> responseSupplier) {
        this.request = request;
        this.responseSupplier = responseSupplier;
    }

    public void supplyResponse(HttpServletResponse response) {
        this.responseSupplier.complete(response);
    }

    public CompletableFuture<HttpServletResponse> getSupplier() {
        return responseSupplier;
    }
    public void doGET(HttpServletRequest request, HttpServletResponse response) throws ExecutionException, InterruptedException {
        HttpRequestResponse responseSupplier = new HttpRequestResponse(request, new CompletableFuture<>());
        String id;

        requests.put(EndpointConfig.queryWeatherById + id, HashMap.class) responseSupplier);
        response = responseSupplier.getSupplier().get();
    }
    public void doPOST(HttpServletRequest request, HttpServletResponse response) {
        HttpRequestResponse responseSupplier = requests.remove(request);
        responseSupplier.getSupplier().complete(response);
    }
}

