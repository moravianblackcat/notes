package cz.dan.notes.integration.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class ClassPathResourceUtil {

    private final ObjectMapper objectMapper;

    public String getStringFromJsonPath(String jsonPath) {
        try {
            ClassPathResource resource = new ClassPathResource(jsonPath);
            byte[] bytes = resource.getInputStream().readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON from path: " + jsonPath, e);
        }
    }

    public Map<String, Object> getMapFromJsonPath(String jsonPath) {
        try {
            ClassPathResource resource = new ClassPathResource(jsonPath);
            return objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON as Map from path: " + jsonPath, e);
        }
    }
}
