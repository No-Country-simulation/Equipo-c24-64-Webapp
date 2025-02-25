package gestionDeReservas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary getCloudinary(){
        Dotenv dotenv = Dotenv.load();
        Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
        return cloudinary;
    }
}
