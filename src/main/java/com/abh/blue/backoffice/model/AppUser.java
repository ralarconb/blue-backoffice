package com.abh.blue.backoffice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AppUser {
	private final static String appuser_sequence = "appuser_sequence";
	@Id
	@SequenceGenerator(name = appuser_sequence, sequenceName = appuser_sequence, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = appuser_sequence)
	private Long id;
	private String email;
	private String password;
	private Boolean locked = false;
	private Boolean enabled = false;
	private String firstName;
	private String lastName;
}
