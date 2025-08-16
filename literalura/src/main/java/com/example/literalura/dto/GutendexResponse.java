package com.example.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponse {
    private java.util.List<GutendexBookDTO> results;

    public java.util.List<GutendexBookDTO> getResults() { return results; }
    public void setResults(java.util.List<GutendexBookDTO> results) { this.results = results; }
}
