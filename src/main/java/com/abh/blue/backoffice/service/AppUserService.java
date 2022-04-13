package com.abh.blue.backoffice.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.abh.blue.backoffice.model.AppUser;
import com.abh.blue.backoffice.model.ConfirmationToken;
import com.abh.blue.backoffice.repo.AppUserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
	private final AppUserRepo appUserRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenService confirmationTokenService;

	public AppUser getUserByEmail(String email) {
		String message = String.format("User with email %s doesn't exists", email);
		AppUser user = appUserRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException(message));
		return user;
	}

	public String signUpUser(AppUser appUser) {
		String email = appUser.getEmail();
		boolean userExists = appUserRepository.findByEmail(email).isPresent();
		if (userExists) {
			throw new IllegalStateException(String.format("Email %s already taken", email));
		}

		String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
		appUser.setPassword(encodedPassword);
		appUserRepository.save(appUser);

		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15), appUser);
		confirmationTokenService.saveConfirmationToken(confirmationToken);

		// TODO: SEND EMAIL and tell the user that him has been registered successfully

		return token;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public int enableAppUser(String email) {
		return appUserRepository.enableAppUser(email);
	}
}
