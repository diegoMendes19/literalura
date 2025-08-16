package com.example.literalura.client;

import com.example.literalura.dto.GutendexBookDTO;
import com.example.literalura.dto.GutendexResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class GutendexClient {

    private static final String BASE_URL = "https://gutendex.com/books/?search=";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Busca na Gutendex e retorna o primeiro livro encontrado pelo título, se existir.
     */
    public Optional<GutendexBookDTO> buscarPrimeiroLivroPorTitulo(String titulo) {
        try {
            String encoded = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + encoded))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.err.println("Falha na chamada à API. Status: " + response.statusCode());
                return Optional.empty();
            }

            GutendexResponse gutendex = objectMapper.readValue(response.body(), GutendexResponse.class);
            if (gutendex.getResults() == null || gutendex.getResults().isEmpty()) {
                return Optional.empty();
            }
            return Optional.ofNullable(gutendex.getResults().get(0)); // mantém apenas o primeiro resultado
        } catch (Exception e) {
            System.err.println("Erro ao consultar API: " + e.getMessage());
            return Optional.empty();
        }
    }
}
