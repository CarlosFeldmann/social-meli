package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ObjectMapperConfig {
  private static final String DATE_FORMAT = "dd-MM-yyyy";

  @Bean
  @Primary
  public ObjectMapper configureObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();

    objectMapper.registerModule(createTimeModule());

    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    objectMapper.setDateFormat(sdf);
    return objectMapper;
  }

  private JavaTimeModule createTimeModule() {
    /*
     * The default objectMapper.setDateFormat doesn't work with LocalDate and LocalDateTime
     * So, since we need to receive and return LocalDate in another pattern we need to
     * register a custom serializer and deserializer to handle this pattern
     */
    var module = new JavaTimeModule();
    module
        .addSerializer(LocalDate.class, createLocalDateJsonSerializer())
        .addDeserializer(LocalDate.class, createLocalDateJsonDeserializer());

    return module;
  }

  private JsonSerializer<LocalDate> createLocalDateJsonSerializer() {
    return new JsonSerializer<>() {
      @Override
      public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers)
          throws IOException {
        gen.writeString(value.format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
      }
    };
  }

  private JsonDeserializer<LocalDate> createLocalDateJsonDeserializer() {
    return new JsonDeserializer<>() {
      @Override
      public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return LocalDate.parse(p.getValueAsString(), DateTimeFormatter.ofPattern(DATE_FORMAT));
      }
    };
  }
}
