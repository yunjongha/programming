package configs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import configs.jms.Client;

@SpringBootApplication
public class SpringConfigurationApplication {

	@Bean
	public MappingJackson2MessageConverter messageConverter() {
		MappingJackson2MessageConverter messageConverter =
				new MappingJackson2MessageConverter();
		messageConverter.setTypeIdPropertyName("_typeId");

		Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
		typeIdMappings.put("client", Client.class);
		messageConverter.setTypeIdMappings(typeIdMappings);

		return messageConverter;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringConfigurationApplication.class, args);
	}

}
