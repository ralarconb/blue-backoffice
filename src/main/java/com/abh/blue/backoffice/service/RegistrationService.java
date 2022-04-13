package com.abh.blue.backoffice.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.client.RestTemplate;

import com.abh.blue.backoffice.config.Config;
import com.abh.blue.backoffice.model.AppUser;
import com.abh.blue.backoffice.model.ConfirmationToken;
import com.abh.blue.backoffice.model.request.RegistrationRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {
	private final static Logger logger = LoggerFactory.getLogger(RegistrationService.class);
	private final EmailValidatorService emailValidatorService;
	private final AppUserService appUserService;
	private final ConfirmationTokenService confirmationTokenService;
	private final Config config;

	public String register(RegistrationRequest request) {
		String email = request.getEmail();
		boolean isValidEmail = emailValidatorService.test(email);
		if (!isValidEmail) {
			throw new IllegalStateException(String.format("Email %s is not valid", email));
		}

		AppUser appUser = new AppUser();
		appUser.setFirstName(request.getFirstName());
		appUser.setLastName(request.getLastName());
		appUser.setEmail(email);
		appUser.setPassword(request.getPassword());
		String token = appUserService.signUpUser(appUser);

		String endpoint = config.getUrlregistration() + token;
		logger.info(endpoint);
		callEmailApi(email, endpoint);

		return token;
	}

	private void callEmailApi(final String to, String endpoint) {
		logger.info("call email api");
//		String from = "communication@rab.com";
//		String subject = "One last step!";
//		String body = String.format(
//				"<html><body><div>Hi!</div><p>Thank you for registering. Please click on the below link to activate your account:</p><blockquote><a href='%s'>Activate Now</a> Link will expire in 15 minutes.</blockquote><p>See you soon</p></body></html>",
//				endpoint);
//		String emailEndPoint = config.getApiEmailConfirm();
//		RestTemplate restTemplate = new RestTemplate();
//		EmailRequest emailRequest = new EmailRequest();
//		emailRequest.setFrom(from);
//		emailRequest.setTo(to);
//		emailRequest.setSubject(subject);
//		emailRequest.setBody(body);
//		restTemplate.postForEntity(emailEndPoint, emailRequest, null);
	}

	@Transactional
	public String confirm(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
				.orElseThrow(() -> new IllegalStateException("Token not found"));
		if (confirmationToken.getConfirmedAt() != null) {
			throw new IllegalStateException("Email already confirmed");
		}

		LocalDateTime expiredAt = confirmationToken.getExpiresAt();
		if (expiredAt.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("Token expired");
		}

		confirmationTokenService.setConfirmedAt(token);
		int code = appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());

		return String.format("Confirmed %s", code);
	}
}
