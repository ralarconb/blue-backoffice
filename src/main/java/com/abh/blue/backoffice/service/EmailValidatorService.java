package com.abh.blue.backoffice.service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class EmailValidatorService implements Predicate<String> {
	private final static String regex = "^(.+)@(.+)$";

	@Override
	public boolean test(String email) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
