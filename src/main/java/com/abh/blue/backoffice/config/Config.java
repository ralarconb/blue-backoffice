package com.abh.blue.backoffice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("endpoint")
@Getter
@Setter
public class Config {
	private String urlregistration;
}
