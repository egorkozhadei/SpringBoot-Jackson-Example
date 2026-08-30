package com.egor.jackson;

import com.egor.jackson.domain.Book;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JacksonTests {

    @Test
    public void testThatObjectMapperCanCreateJsonFromObject() {
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = Book.builder()
                .isbn("978-0-13-478627-5")
                .title("The Enigma of Eternity")
                .author("Aria Montgomery")
                .yearPublished("2005")
                .build();

        String result = objectMapper.writeValueAsString(book);
        assertThat(result).isEqualTo("{\"isbn\":\"978-0-13-478627-5\",\"title\":\"The Enigma of Eternity\",\"author\":\"Aria Montgomery\",\"yearPublished\":\"2005\"}");
    }

}
