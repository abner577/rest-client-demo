package dev.abner.rest_client_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

import static org.springframework.web.client.RestClient.builder;

@SpringBootApplication
public class RestClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestClientDemoApplication.class, args);
	}


	@Bean
	public RestClient restClient(RestClient.Builder builder) {
		return builder
				.baseUrl("https://jsonplaceholder.typicode.com")
				.build();
	}

}
