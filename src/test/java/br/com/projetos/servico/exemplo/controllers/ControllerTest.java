package br.com.projetos.servico.exemplo.controllers;

import br.com.projetos.libs.configs.message.MessageLocale;
import br.com.projetos.libs.exceptions.ErrorCode;
import br.com.projetos.servico.exemplo.Application;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static io.restassured.config.DecoderConfig.decoderConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class ControllerTest {

    private static final String BASE_URI = "http://localhost/servico/exemplo/api";
    public static final String PATH_STATUS = "status";
    public static final String PATH_ERROR_TITLE = "title";

    @LocalServerPort
    private int port;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MessageLocale messageLocale;

    @BeforeAll
    public static void init() {
        RestAssured.config = config().decoderConfig(decoderConfig().defaultContentCharset(StandardCharsets.UTF_8));
    }

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }

    public Response getRequest(String url) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .when()
                .get(url)
                .then()
                .extract().response();
    }

    public Response postRequest(String url, Object body) {
        String requestBody = objectToJson(body);
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post(url)
                .then()
                .extract().response();
    }

    public Response putRequest(String url, Object body) {
        String requestBody = objectToJson(body);
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .put(url)
                .then()
                .extract().response();
    }

    public Response patchRequest(String url, Object body) {
        String requestBody = objectToJson(body);
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .patch(url)
                .then()
                .extract().response();
    }

    public void assertErrorStatusAndTitle(Response response, HttpStatus status, ErrorCode msgEnum) {
        String message = messageLocale.getMessage(msgEnum);

        assertEquals(status.value(), response.statusCode());
        assertEquals(status.value(), response.jsonPath().getInt(PATH_STATUS));
        assertEquals(message, response.jsonPath().getString(PATH_ERROR_TITLE));
    }

    private String objectToJson(Object body) {
        try {
            return objectMapper.writeValueAsString(body);
        } catch (Exception ignored) {}
        return "{}";
    }


}
