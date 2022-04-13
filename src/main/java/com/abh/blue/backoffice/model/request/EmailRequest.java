package com.abh.blue.backoffice.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EmailRequest {
	private String from;
	private String to;
	private String subject;
	private String body;
}
