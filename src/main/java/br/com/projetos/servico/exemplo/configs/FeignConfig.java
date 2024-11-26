package br.com.projetos.servico.exemplo.configs;

import feign.FeignException;
import feign.Request;
import feign.RetryableException;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            if (HttpStatus.valueOf(response.status()).is5xxServerError() && response.request().httpMethod().equals(Request.HttpMethod.GET)){
                return new RetryableException(response.status(), methodKey, response.request().httpMethod(), (Long) null, response.request());
            }
            return FeignException.errorStatus(methodKey, response);
        };
    }

    @Bean
    Retryer retryer(@Value("${feign.client.retryer.period}") long period,
                    @Value("${feign.client.retryer.max-period}") long maxPeriod,
                    @Value("${feign.client.retryer.max-attempts}") int maxAttempts) {
        return new Retryer.Default(period, maxPeriod, maxAttempts);
    }
}
