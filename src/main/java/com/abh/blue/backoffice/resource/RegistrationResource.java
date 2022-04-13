package com.abh.blue.backoffice.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abh.blue.backoffice.model.request.RegistrationRequest;
import com.abh.blue.backoffice.service.RegistrationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class RegistrationResource {
	private final RegistrationService registrationService;

	@PostMapping
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	}

	@GetMapping(path = "confirm")
	public String confirm(@RequestParam("token") String token) {
		return registrationService.confirm(token);
	}
}
