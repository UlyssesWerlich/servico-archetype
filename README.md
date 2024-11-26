# Serviço exemplo

> Aplicação modelo. 

[![Java Version][java-image]][java-url]
[![Spring Version][spring-image]][spring-url]

Esta aplicação serve de base para a construção de novas aplicações da camada de Serviço.


## Importação dos Endpoints

Com a ferramenta OpenAPI é possível importar todos os endpoints da aplicação, disponível na URL da aplicação abaixo:

http://localhost:8080/servico/exemplo/api/openapi


## Catálogo de Versões (BOM)

O Catálogo de Versões é uma dependência que padroniza as bibliotecas e suas versões, tanto internas quanto externas. 

Após feito a atualização do gradle no projeto, onde o catálogo é recarregado, as libs ficam disponíveis na variável *bom* conforme abaixo

```kotlin
    implementation(bom.springboot.starter.web)
```

## Dependência de Configuração

A aplicação utiliza a dependência libs-exemplo, disponibilizado no repositório.

Essa dependência contêm os principais beans e as classes de utilitários.

Para instanciar os beans na classe Application é feito o escaneamento no libs-exemplo dos pacotes onde estes beans estão localizados.

```java
@SpringBootApplication(scanBasePackages = {
        BeansBasePackages.APM,
        BeansBasePackages.JACKSON,
        BeansBasePackages.MESSAGE,
        ApplicationBasePackage.SERVICO
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```


## Configuração de Desenvolvimento

### Build

Para fazer o build da aplicação deve ser executado o seguinte comando.

```sh
./gradlew clean build --refresh-dependencies
```

## Documentação

<!-- Markdown link & img dfn's -->

[java-image]: https://img.shields.io/badge/Java-v21-yellow
[spring-image]: https://img.shields.io/badge/Spring--Boot-v3.2.2-green
[java-url]: https://www.oracle.com/java/technologies/javase/21u-relnotes.html
[spring-url]: https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-dependencies/3.2.2