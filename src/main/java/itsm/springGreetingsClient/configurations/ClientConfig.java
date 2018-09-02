package itsm.springGreetingsClient.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import itsm.springGreetingsClient.client.SpringGreetingsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:client.properties")
public class ClientConfig {


    @Value("${host.name}")
    private String hostName;

    @Value("${host.port}")
    private Integer port;

    @Value("${client.name}")
    private String name;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public SpringGreetingsClient client(ObjectMapper mapper) {
        return  new SpringGreetingsClient(hostName, port, name, objectMapper());
    }
}
