package com.abh.blue.backoffice.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.abh.blue.backoffice.model.ConfirmationToken;
import com.abh.blue.backoffice.repo.ConfirmationTokenRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
	private final ConfirmationTokenRepo confirmationTokenRepo;

	public Optional<ConfirmationToken> getToken(String token) {
		return confirmationTokenRepo.findByToken(token);
	}

	public int setConfirmedAt(String token) {
		return confirmationTokenRepo.updateConfirmedAt(token, LocalDateTime.now());
	}

	public void saveConfirmationToken(ConfirmationToken confirmationToken) {
		confirmationTokenRepo.save(confirmationToken);
	}
}
