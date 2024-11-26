package br.com.projetos.servico.exemplo.controllers.v1;

import br.com.projetos.servico.exemplo.controllers.ControllerTest;
import br.com.projetos.servico.exemplo.models.dtos.DbTabelaPadraoRequestDto;
import br.com.projetos.servico.exemplo.models.entities.DbTabelaPadrao;
import br.com.projetos.servico.exemplo.models.enums.ColunaEnum;
import br.com.projetos.servico.exemplo.repositories.DbTabelaPadraoRepository;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static br.com.projetos.libs.handler.HandlerCodeEnum.HANDLER_BUSINESS_EXCEPTION;
import static br.com.projetos.libs.handler.HandlerCodeEnum.HANDLER_METHOD_ARGUMENT_NOT_VALID_EXCEPTION;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpStatus.*;

class DbTabelaPadraoControllerTest extends ControllerTest {

    private static final String ENDPOINT = "/v1/tabela-padrao";
    private static final String PATH_ID = "id";

    private final DbTabelaPadraoRepository repository;
    @Autowired
    DbTabelaPadraoControllerTest(DbTabelaPadraoRepository repository) {
        this.repository = repository;
    }

    @Test
    @Order(1)
    void criarDbTabelaPadrao_quandoDadosValidosInformados_deveCriarERetornarId() {
        DbTabelaPadraoRequestDto request = buildRequestDto();
        Response response = postRequest(ENDPOINT, request);

        assertThat(response.statusCode(), equalTo(CREATED.value()));
        assertCreated(response.jsonPath().getString(PATH_ID), request);
    }

    @Test
    @Order(2)
    void criarDbTabelaPadrao_quandoColunaTextoNaoInformado_deveRetornarErro() {
        DbTabelaPadraoRequestDto request = buildRequestDto();
        request.setColunaTexto(null);
        Response response = postRequest(ENDPOINT, request);

        assertErrorStatusAndTitle(response, BAD_REQUEST, HANDLER_METHOD_ARGUMENT_NOT_VALID_EXCEPTION);
    }

    @Test
    @Order(3)
    void criarDbTabelaPadrao_quandoColunaEnumMaiorQue5_deveRetornarErro() {
        DbTabelaPadraoRequestDto request = buildRequestDto();
        request.setColunaEnum(5);
        Response response = postRequest(ENDPOINT, request);

        assertErrorStatusAndTitle(response, BAD_REQUEST, HANDLER_METHOD_ARGUMENT_NOT_VALID_EXCEPTION);
    }

    @Test
    @Order(4)
    void buscarDbTabelaPadrao_quandoIdExistente_deveRetornarObjeto() {
        String pathId = "1";
        Response response = getRequest(ENDPOINT.concat("/").concat(pathId));

        assertThat(response.statusCode(), equalTo(OK.value()));
        assertThat(response.jsonPath().getString(PATH_ID), equalTo(pathId));
    }

    @Test
    @Order(5)
    void buscarDbTabelaPadrao_quandoIdInexistente_deveRetornarNotFound() {
        String pathId = "-1";
        Response response = getRequest(ENDPOINT.concat("/").concat(pathId));

        assertThat(response.statusCode(), equalTo(NOT_FOUND.value()));
        assertErrorStatusAndTitle(response, NOT_FOUND, HANDLER_BUSINESS_EXCEPTION);
    }

    @Test
    @Order(6)
    void atualizarDbTabelaPadrao_quandoDadosValidosInformados_deveAtualizarERetornarId() {
        String pathId = "1";
        DbTabelaPadraoRequestDto request = buildRequestDto();
        request.setColunaNumero(15);
        Response response = patchRequest(ENDPOINT.concat("/").concat(pathId), request);

        assertThat(response.statusCode(), equalTo(OK.value()));
        assertCreated(response.jsonPath().getString(PATH_ID), request);
    }


    private void assertCreated(String id, DbTabelaPadraoRequestDto request) {
        assertThat(id, notNullValue());

        Optional<DbTabelaPadrao> dbTabelaPadraoOptional = repository.findById(Long.valueOf(id));
        assertThat(dbTabelaPadraoOptional.isPresent(), Matchers.is(true));

        DbTabelaPadrao dbTabelaPadrao = dbTabelaPadraoOptional.get();
        assertThat(dbTabelaPadrao.getColunaTexto(), equalTo(request.getColunaTexto()));
        assertThat(dbTabelaPadrao.getColunaNumero(), equalTo(request.getColunaNumero()));
        assertThat(dbTabelaPadrao.getColunaDecimal().longValue(), equalTo(request.getColunaDecimal().longValue()));
        assertThat(dbTabelaPadrao.getColunaEnum(), equalTo(ColunaEnum.fromValue(request.getColunaEnum())));
        assertThat(dbTabelaPadrao.getColunaDt(), equalTo(request.getColunaDt()));
    }

    private DbTabelaPadraoRequestDto buildRequestDto() {
        DbTabelaPadraoRequestDto dto = new DbTabelaPadraoRequestDto();
        dto.setColunaTexto("TESTE");
        dto.setColunaNumero(10);
        dto.setColunaDecimal(BigDecimal.valueOf(10.00));
        dto.setColunaEnum(1);
        dto.setColunaDt(LocalDate.now());
        return dto;
    }
}
