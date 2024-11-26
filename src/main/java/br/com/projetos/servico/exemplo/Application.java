package br.com.projetos.servico.exemplo;

import br.com.projetos.libs.ApplicationBasePackage;
import br.com.projetos.libs.configs.BeansBasePackages;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@OpenAPIDefinition(
		info = @Info(
				title = "Servico Exemplo",
				version = "0.4.3",
				description = "Aplicação Exemplo do serviço"
		)
)
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {
		BeansBasePackages.APM,
		BeansBasePackages.JACKSON,
		BeansBasePackages.MESSAGE,
		BeansBasePackages.HTTPCLIENT,
		BeansBasePackages.AUDITORIA,
		ApplicationBasePackage.SERVICO
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
