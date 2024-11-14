package dev.erickystn.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(
        String title,
        List<String> languages,
        List<AutorDTO> authors,
        @JsonProperty("download_count") Integer downloads



) {
}
