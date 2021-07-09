package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Configuration
public class SpringConfig {
    private static final String DATE_FORMAT = "dd-MM-yyyy'T'HH:mm:ss'Z'";

    @Bean
    @Primary
    public ObjectMapper configureObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        objectMapper.setDateFormat(sdf);
        return objectMapper;
    }
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        // Since our application is a microservice doesn't make sense return error messages in portuguese
        slr.setDefaultLocale(Locale.ENGLISH);
        return slr;
    }
}
