package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ApiClient {
    public static void main(String[] args) {
        String url = "http://localhost:8080/api/enemigo";
        ExecutorService poolHilos = Executors.newFixedThreadPool(4);

        // Un solo cliente HTTP es suficiente y más eficiente
        HttpClient client = HttpClient.newHttpClient();

        // Request JSON común para las peticiones
        HttpRequest jsonRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .build();

        // Petición JSON 1 en hilo separado
        poolHilos.submit(() -> {
            client.sendAsync(jsonRequest, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(response -> {
                        System.out.println("JSON Response 1:");
                        System.out.println(response);
                    })
                    .join();
        });

        // Petición JSON 2 en hilo separado
        poolHilos.submit(() -> {
            client.sendAsync(jsonRequest, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(response -> {
                        System.out.println("JSON Response 2:");
                        System.out.println(response);
                    })
                    .join();
        });

        // Petición JSON 3 en hilo separado
        poolHilos.submit(() -> {
            client.sendAsync(jsonRequest, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(response -> {
                        System.out.println("JSON Response 3:");
                        System.out.println(response);
                    })
                    .join();
        });

        // Petición JSON 4 en hilo separado
        poolHilos.submit(() -> {
            client.sendAsync(jsonRequest, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(response -> {
                        System.out.println("JSON Response 4:");
                        System.out.println(response);
                    })
                    .join();
        });

        // Petición XML en hilo separado
        HttpRequest xmlRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "text/xml")
                .build();

        poolHilos.submit(() -> {
            client.sendAsync(xmlRequest, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(response -> {
                        System.out.println("XML Response:");
                        System.out.println(response);
                    })
                    .join();
        });

        // Cerrar el pool de hilos correctamente
        poolHilos.shutdown();
        try {
            // Esperar a que terminen todas las tareas (máximo 30 segundos)
            if (!poolHilos.awaitTermination(30, TimeUnit.SECONDS)) {
                poolHilos.shutdownNow();
            }
        } catch (InterruptedException e) {
            poolHilos.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}