package com.org.lob.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.org.lob.std.support.SpringSecurityAuditorAware;

@Configuration
@EnableJpaAuditing
class RepositoryConfig {

	@Bean
	AuditorAware<String> auditorProvider() {
		return new SpringSecurityAuditorAware();
	}
}