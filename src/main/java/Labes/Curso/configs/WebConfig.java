package Labes.Curso.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Tipo de Configuração
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    
    //Para configurar o CORS
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**");
    }
}
