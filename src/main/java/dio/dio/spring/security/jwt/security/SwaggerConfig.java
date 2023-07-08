package dio.dio.spring.security.jwt.security;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
  private static final Set<String> DEFAULT_PRODUCES_CONSUMES = new HashSet<>(Arrays.asList("application/json"));
  
  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String DEFAULT_AUTHORIZATION_SCOPE = "global";
  private static final String DEFAULT_AUTHORIZATION_DESCRIPTION = "Authorization header using the JWT token";
  
  private static final String[] AUTH_WHITELIST = {
    // Aqui você pode adicionar os endpoints que não exigem autenticação
    // "/example/public",
    // "/example/login"
  };
  
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("api")
        .apiInfo(apiInfo())
        .produces(DEFAULT_PRODUCES_CONSUMES)
        .consumes(DEFAULT_PRODUCES_CONSUMES)
        .select()
        .apis(RequestHandlerSelectors.basePackage("dio.dio.spring.security.jwt.controller"))
        .paths(PathSelectors.any())
        .build()
        .securityContexts(Collections.singletonList(securityContext()))
        .securitySchemes(Collections.singletonList(apiKey()));
  }
  
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Título da API")
        .description("Descrição da API")
        .version("1.0.0")
        .build();
  }
  
  private ApiKey apiKey() {
    return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
  }
  
  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(Collections.singletonList(defaultAuth()))
        .build();
  }
  
  private SecurityReference defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope(DEFAULT_AUTHORIZATION_SCOPE, DEFAULT_AUTHORIZATION_DESCRIPTION);
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
    return new SecurityReference("JWT", authorizationScopes);
  }
  
  // Configuração adicional para evitar solicitação de token JWT em determinados endpoints
  @Bean
  public Docket publicApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("public-api")
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("dio.dio.spring.security.jwt.controller"))
        .paths(PathSelectors.ant("/example/public/**"))
        .build()
        .securityContexts(Collections.singletonList(publicSecurityContext()));
  }
  
  private SecurityContext publicSecurityContext() {
    return SecurityContext.builder()
        .forPaths(PathSelectors.ant("/example/public/**"))
        .build();
  }
}
