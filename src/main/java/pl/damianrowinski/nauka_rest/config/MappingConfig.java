package pl.damianrowinski.nauka_rest.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MappingConfig {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
