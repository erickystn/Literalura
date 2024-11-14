package dev.erickystn.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Conversor {
    ObjectMapper mapper = new ObjectMapper();

    public <T> T converterDados(String dadosJson, Class<T> classe) throws JsonProcessingException {

        if (containsResults(dadosJson)) {
            var resultsNode = mapper.readTree(dadosJson).path("results");
            return mapper.treeToValue(resultsNode.get(0), classe);
        }

        return null;
    }

    public boolean containsResults(String dadosJson) {
        try {
            var resultsNode = mapper.readTree(dadosJson).path("results");
            return resultsNode != null && resultsNode.isArray() && !resultsNode.isEmpty();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }
    }


}
