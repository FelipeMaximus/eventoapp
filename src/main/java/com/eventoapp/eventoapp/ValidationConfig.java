package com.eventoapp.eventoapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

//CLASSE DE VALIDAÇÃO PARA CAMPOS VAZIOS
@Configuration
public class ValidationConfig {
	
	@Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

}
