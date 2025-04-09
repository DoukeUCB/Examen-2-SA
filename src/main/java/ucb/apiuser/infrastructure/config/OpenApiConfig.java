package ucb.apiuser.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI userApiDoc() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Usuarios")
                        .description("API RESTful para la gestión de usuarios implementando arquitectura hexagonal")
                        .version("1.0.0"));
    }
}
