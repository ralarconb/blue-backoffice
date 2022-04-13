package com.abh.blue.backoffice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.abh.blue.backoffice.model.request.RegistrationRequest;
import com.abh.blue.backoffice.service.RegistrationService;

@Configuration
public class AppUserConfig {
	@Bean
	CommandLineRunner commandLineRunner(RegistrationService registrationService) {
		return args -> {
			RegistrationRequest request1 = new RegistrationRequest("Paolo", "Castillo", "pcastillo@abh.com", "s3cret");
			String token1 = registrationService.register(request1);
			registrationService.confirm(token1);
			RegistrationRequest request2 = new RegistrationRequest("Sergio", "Palacios", "spalacios@abh.com", "s3cret");
			String token2 = registrationService.register(request2);
			registrationService.confirm(token2);
		};
	}
}
