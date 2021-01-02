package com.devsuperior.dscatalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {
	
	@Bean // Com o Bean, estamos dizendo que essa instância vai ser um componente
	// gerenciado pelo SpringBoot
	public BCryptPasswordEncoder passwordEncoder() { // passwordEncoder é o nome dado ao nosso método
		return new BCryptPasswordEncoder();
		
	}

}
