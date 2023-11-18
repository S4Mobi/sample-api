package com.s4mobi.application;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@SpringBootApplication
@EnableFeignClients("com.s4mobi")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@OpenAPIDefinition(servers = {@Server(url = "${server.servlet.context-path}", description = "Default Server URL")})
public class Application {

	@Value("${spring.security.basic.enabled}")
	private boolean securityBasicEnabled;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		if (!securityBasicEnabled) {
			http.csrf().disable();
			http.httpBasic().disable();
		}
		return http.build();
	}
}
