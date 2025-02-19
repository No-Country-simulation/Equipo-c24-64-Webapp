package gestionDeReservas.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de ejemplo",
                version = "1.0.0",
                description = "Documentación de la API",
                contact = @Contact(
                        name = "Messi",
                        email = "LionelMessi10@email.com"
                ),
                license = @License(name = "Licencia")
        )
)
public class OpenApiConfig {
    
}