package rs.ac.np.police.trafficis.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Development server");

        Contact contact = new Contact();
        contact.setName("Ela Curić");
        contact.setEmail("curicela@gmail.com");

        Info info = new Info()
                .title("Informacioni sistem policijske stanice za bezbednost saobraćaja - API")
                .version("1.0.0")
                .description("REST API za upravljanje saobraćajnim incidentima, kaznama, vozačima, vozilima i signalizacijom")
                .contact(contact)
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0"));

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
}