package com.abh.blue.backoffice.resource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abh.blue.backoffice.model.AppUser;
import com.abh.blue.backoffice.service.AppUserService;

@RestController
@RequestMapping(path = "/user")
public class AppUserResource {
	private final AppUserService appUserService;

	@Autowired
	public AppUserResource(AppUserService pUserService) {
		appUserService = pUserService;
	}

	@GetMapping("/{email}")
	public ResponseEntity<AppUser> getUserByEmail(@PathVariable("email") String email) {
		AppUser user = appUserService.getUserByEmail(email);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> userRequest) {
		String email = userRequest.get("email");
		AppUser user = appUserService.getUserByEmail(email);
		if (user == null) {
			throw new IllegalStateException(String.format("User with email %s not found!", email));
		}
		// TODO decrypt password
		String password = userRequest.get("password");
		if (!password.equals(user.getPassword())) {
			// TODO lock user after 3 consecutive errors
			throw new IllegalStateException(String.format("User or password incorrect!", email));
		}
		Map<String, String> map = new HashMap<String, String>();
		// TODO set token
		map.put("token", password);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
