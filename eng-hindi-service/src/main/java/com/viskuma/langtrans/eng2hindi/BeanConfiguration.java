package com.viskuma.langtrans.eng2hindi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {

	@Bean
	public RestTemplate createRestTemplate() {
		return new RestTemplate();
	}
}
