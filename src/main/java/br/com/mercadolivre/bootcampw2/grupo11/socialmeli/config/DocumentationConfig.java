package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class DocumentationConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Social Meli API")
                        .description(
                                "API for creating following and post system to connect sellers and costumers")
                        .version("0.0.1")

                )
                .externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/lmarizmeli/social-meli/"));
    }


    /**
     * Sorting all the resources in the swagger yaml
     * I want the controllers and models in a given order
     */
    @Bean
    public OpenApiCustomiser sortSchemasByType() {
        return openApi -> {

            var schemas = openApi.getComponents().getSchemas().values()
                    .stream()
                    .sorted(schemaComparator())
                    .collect(Collectors.toMap(
                            Schema::getName,
                            Function.identity(),
                            (p1, p2) -> p1,
                            LinkedHashMap::new
                    ));

            openApi.getComponents().setSchemas(schemas);

            var tags = openApi.getTags()
                    .stream()
                    .sorted(tagComparator())
                    .collect(Collectors.toList());

            openApi.setTags(tags);
        };
    }

    private boolean isForm(Schema<?> schema) {
        return schema.getName().toLowerCase().endsWith("form");
    }

    private boolean isError(Schema<?> schema) {
        return schema.getName().toLowerCase().endsWith("error");
    }

    private Comparator<Schema> schemaComparator() {
        return (s1, s2) -> {
            // Forms should be first
            if (isForm(s1)) {
                return -1;
            } else if (isForm(s2)) {
                return 1;
            }
            // And errors in the end
            if (isError(s1)) {
                return 1;
            } else if (isError(s2)) {
                return -1;
            }
            // Otherwise name comparison works good enough
            return s1.getName().compareToIgnoreCase(s2.getName());
        };
    }


    private Comparator<Tag> tagComparator() {
        var order = Arrays.asList(
                "User Controller",
                "Follow Controller",
                "Products/Posts Controller",
                "Promotional Post Controller"
        );

        return (t1, t2) -> {
            int i1 = order.indexOf(t1.getName());
            int i2 = order.indexOf(t2.getName());

            if (i1 >= 0 || i2 >= 0) {
                // If the first item is not in the list, it should be after the one that is
                if (i1 == -1) {
                    return 1;
                }
                // If element is in the list and the other one isn't, then should be first
                if (i2 == -1) {
                    return -1;
                }
                // Return the position relative to the order list
                return i1 - i2;
            }
            // If none of the tags are in the list, compare by name
            return t1.getName().compareToIgnoreCase(t2.getName());
        };
    }


}
