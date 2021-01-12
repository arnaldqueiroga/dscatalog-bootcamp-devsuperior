package com.devsuperior.dscatalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AppConfig {
	
	@Bean // Com o Bean, estamos dizendo que essa instância vai ser um componente
	// gerenciado pelo SpringBoot
	public BCryptPasswordEncoder passwordEncoder() { // passwordEncoder é o nome dado ao nosso método
		return new BCryptPasswordEncoder();
		
	}
	
	// Os dois Beans são objetos que irão acessar o Token JWT
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("MY-JWT-SECRET");
		return tokenConverter;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

}
