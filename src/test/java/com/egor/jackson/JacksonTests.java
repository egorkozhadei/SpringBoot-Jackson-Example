package com.egor.jackson;

import com.egor.jackson.domain.Book;
import org.junit.jupiter.api.Test;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.exc.UnrecognizedPropertyException;
import tools.jackson.databind.json.JsonMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JacksonTests {
    @Test
    public void testThatObjectMapperCanCreateJsonFromObject() throws JacksonException {
        final ObjectMapper objectMapper = new ObjectMapper();
        Book book = Book.builder()
                .isbn("978-0-13-478627-5")
                .title("The Enigma of Eternity")
                .author("Aria Montgomery")
                .yearPublished("2005")
                .build();

        String result = objectMapper.writeValueAsString(book);
        assertThat(result).isEqualTo("{\"isbn\":\"978-0-13-478627-5\",\"title\":\"The Enigma of Eternity\",\"author\":\"Aria Montgomery\",\"year\":\"2005\"}");
    }

    @Test
    public void testThatObjectMapperCanCreateJavaObjectFromJsonObject() throws JacksonException {
        Book book = Book.builder()
                .isbn("978-0-13-478627-5")
                .title("The Enigma of Eternity")
                .author("Aria Montgomery")
                .yearPublished("2005")
                .build();
        String json = "{\"isbn\":\"978-0-13-478627-5\",\"title\":\"The Enigma of Eternity\",\"author\":\"Aria Montgomery\",\"year\":\"2005\"}";
        final ObjectMapper objectMapper = new ObjectMapper();
        Book result = objectMapper.readValue(json, Book.class);

        assertThat(result).isEqualTo(book);
    }

    @Test
    public void testThatObjectMapperThrowsOnUnknownProperties() throws UnrecognizedPropertyException {
        Book book = Book.builder()
                .isbn("978-0-13-478627-5")
                .title("The Enigma of Eternity")
                .author("Aria Montgomery")
                .yearPublished("2005")
                .build();
        String json = "{\"unknownProperty\":\"unknownValue\",\"isbn\":\"978-0-13-478627-5\",\"title\":\"The Enigma of Eternity\",\"author\":\"Aria Montgomery\",\"year\":\"2005\"}";
        final JsonMapper objectMapper = JsonMapper.builder().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).build();

        assertThrows(UnrecognizedPropertyException.class, () -> objectMapper.readValue(json, Book.class));
    }
}
