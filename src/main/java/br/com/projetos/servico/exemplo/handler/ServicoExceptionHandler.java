package br.com.projetos.servico.exemplo.handler;

import br.com.projetos.libs.configs.message.MessageLocale;
import br.com.projetos.libs.handler.AbstractExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.nio.ByteBuffer;
import java.util.Map;

import static br.com.projetos.libs.handler.HandlerCodeEnum.HANDLER_INTEGRATION_EXCEPTION;
import static br.com.projetos.libs.handler.HandlerCodeEnum.HANDLER_INTEGRATION_EXCEPTION_DETAIL;
import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class ServicoExceptionHandler extends AbstractExceptionHandler {

    @Autowired
    public ServicoExceptionHandler(
            MessageLocale messageLocale,
            ObjectMapper objectMapper,
            @Value("${spring.application.name}") String applicationName,
            @Value("${handler.show-vulnerable-properties:false}") Boolean showVulnerableProperties
    ) {
        super(messageLocale, objectMapper, applicationName, showVulnerableProperties);
    }

    @Override
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignException(FeignException ex, WebRequest request){
        int status = ( ex.status() >= 100 && ex.status() < 600 ) ? ex.status() : INTERNAL_SERVER_ERROR.value();
        Map<String, Object> responseBody = getBodyAsMap(ex.responseBody().map(ByteBuffer::array).orElse(null));
        Map<String, Object> properties = Map.of(
                "httpStatus", ex.status(),
                "responseBody", responseBody,
                "requestUrl", ex.request().url(),
                "requestHttpMethod", ex.request().httpMethod(),
                "requestBody", getBodyAsMap(ex.request().body())
        );
        String title = messageLocale.getMessage(HANDLER_INTEGRATION_EXCEPTION);
        String detail = messageLocale.getMessage(HANDLER_INTEGRATION_EXCEPTION_DETAIL, getMessageBody(responseBody));
        return this.buildResponseEntity(ex, title, detail, HttpStatusCode.valueOf(status), properties);
    }

    public static String getMessageBody(Map<String, Object> responseBody){
        return ofNullable(responseBody.get("error")).map(m -> m.toString().concat(". ")).orElse("") +
                ofNullable(responseBody.get("message")).map(Object::toString).orElse("");
    }
}
