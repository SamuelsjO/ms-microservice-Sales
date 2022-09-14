package br.com.samuel.productapi.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static springfox.documentation.swagger.web.ApiKeyVehicle.HEADER;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends DelegatingWebMvcConfiguration {

    private static final String API_KEY_NAME = "jwt";
    private static final String AUTHORIZATION = "AUTHORIZATION";
    private final String emailContact;
    private final String licenseName;
    private final String licenseUrl;
    private final String title;
    private final String description;
    private final String termOfservices;
    private final String version;


    public SwaggerConfig() {

        emailContact = "samucagm@rocketmail.com";
        licenseName = "Apache 2.0";
        licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.html";
        title = "product-api";
        description = "API Stoke";
        termOfservices = "br.com.samuel.productapi.controller";
        version = "0.0.2-SNAPSHOT";

    }

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .directModelSubstitute(ResponseEntity.class, Void.class)
                .directModelSubstitute(Object.class, Void.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.samuel.productapi.controller"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .consumes(Sets.newHashSet(APPLICATION_JSON_UTF8_VALUE))
                .produces(Sets.newHashSet(APPLICATION_JSON_UTF8_VALUE))
                .apiInfo(metaData())
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()));
    }

    private Contact createContact() {
        return new Contact(null, null, emailContact);
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .license(licenseName)
                .licenseUrl(licenseUrl)
                .contact(createContact())
                .title(title)
                .description(description)
                .termsOfServiceUrl(termOfservices)
                .version(version)
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("**/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiKey apiKey() {
        return new ApiKey(API_KEY_NAME, AUTHORIZATION, HEADER.name());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(authDefinition())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> authDefinition() {
        final var scopes = new AuthorizationScope[0];
        return Lists.newArrayList(
                SecurityReference.builder()
                        .reference(API_KEY_NAME)
                        .scopes(scopes)
                        .build()
        );
    }

}
