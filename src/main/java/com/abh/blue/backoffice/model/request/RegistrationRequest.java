package com.abh.blue.backoffice.model.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String password;
}
