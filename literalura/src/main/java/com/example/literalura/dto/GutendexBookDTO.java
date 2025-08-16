package com.example.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexBookDTO {
    private Integer id;
    private String title;
    private java.util.List<GutendexAuthorDTO> authors;
    private java.util.List<String> languages;

    @JsonAlias("download_count")
    private Integer downloadCount;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public java.util.List<GutendexAuthorDTO> getAuthors() { return authors; }
    public void setAuthors(java.util.List<GutendexAuthorDTO> authors) { this.authors = authors; }
    public java.util.List<String> getLanguages() { return languages; }
    public void setLanguages(java.util.List<String> languages) { this.languages = languages; }
    public Integer getDownloadCount() { return downloadCount; }
    public void setDownloadCount(Integer downloadCount) { this.downloadCount = downloadCount; }
}
