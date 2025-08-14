package com.seguranca.seguranca.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import com.seguranca.seguranca.service.PessoaService;

@TestConfiguration
public class TestConfig {
    
    @Bean
    public PessoaService pessoaService() {
        return new PessoaService();
    }
}
