package lk.ijse.green_shadow_backend.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/field/**")
                .allowedOrigins("http://localhost:5500") // Allow specific origin
                .allowedMethods("GET", "POST")
                .allowedHeaders("*")
                .allowedHeaders("Authorization", "Content-Type")
                .allowCredentials(true);

    }


}
