package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class SpringConfig {

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        // Since our application is a microservice doesn't make sense return error messages in portuguese
        slr.setDefaultLocale(Locale.ENGLISH);
        return slr;
    }
}
