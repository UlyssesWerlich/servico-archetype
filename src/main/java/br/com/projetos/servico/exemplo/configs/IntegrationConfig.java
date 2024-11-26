package br.com.projetos.servico.exemplo.configs;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class IntegrationConfig {
    @Bean
    RequestInterceptor requestInterceptor(@Value("${api.externa.consumer-key}") String consumerKey) {
        return requestTemplate -> requestTemplate.header("x-api-key", consumerKey);
    }
}
