package br.com.projetos.servico.exemplo.services;

import br.com.projetos.servico.exemplo.integrations.FAQIntegration;
import br.com.projetos.servico.exemplo.models.dtos.FAQ;
import br.com.projetos.servico.exemplo.models.dtos.FAQRequestDto;
import br.com.projetos.servico.exemplo.models.dtos.ResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

class FAQServiceTest {

    @InjectMocks
    private FAQService faqService;

    @Mock
    private FAQIntegration FAQIntegration;

    private static final Long ID = 1L;
    private static final String CATEGORIA = "TESTE";
    private static final String PLATAFORMA = "TESTE";
    private static final Integer RELEVANCIA = 10;
    private static final String STATUS = "TESTE";
    private static final String PERGUNTA = "TESTE";
    private static final String RESPOSTA = "TESTE";

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obterFAQPorId_quandoInformadoIdValido_deveRetornarFAQ() {

        when(FAQIntegration.buscarPorId(ID)).thenReturn(buildFAQ());

        FAQ response = faqService.buscarPorId(ID);

        assertThat(response.getId(), equalTo(ID));
    }

    @Test
    void obterFAQPorId_quandoInformadoIdInvalido_deveRetornarNull() {

        when(FAQIntegration.buscarPorId(ID)).thenReturn(null);

        FAQ response = faqService.buscarPorId(ID);

        assertThat(response, equalTo(null));
    }

    @Test
    void obterFAQs_quandoInformadoParametros_deveRetornarListaDeUmObjeto() {

        when(FAQIntegration.listar(CATEGORIA, PLATAFORMA, RELEVANCIA, STATUS, PERGUNTA, RESPOSTA)).thenReturn(buildResponse(Collections.singletonList(buildFAQ())));

        ResponseDTO<FAQ> response = faqService.listar(CATEGORIA, PLATAFORMA, RELEVANCIA, STATUS, PERGUNTA, RESPOSTA);

        assertThat(response.getHasContent(), equalTo(true));
        assertThat(response.getContent().size(), equalTo(1));
        assertThat(response.getContent().getFirst().getId(), equalTo(ID));

    }

    @Test
    void criarFAQ_quandoInformadoRequestDTO_deveRetornarFAQ() {

        when(FAQIntegration.criar(CATEGORIA, PLATAFORMA, RELEVANCIA, STATUS, PERGUNTA, RESPOSTA)).thenReturn(buildFAQ());

        FAQ response = faqService.criar(buildFAQRequestDto());

        assertThat(response.getId(), equalTo(ID));
    }

    @Test
    void atualizarFAQ_quandoInformadoIdComRequestDTO_deveRetornarFAQ() {

        when(FAQIntegration.atualizar(ID, CATEGORIA, PLATAFORMA, RELEVANCIA, STATUS, PERGUNTA, RESPOSTA)).thenReturn(buildFAQ());

        FAQ response = faqService.atualizar(ID, buildFAQRequestDto());

        assertThat(response.getId(), equalTo(ID));
    }

    private static FAQRequestDto buildFAQRequestDto() {
        FAQRequestDto requestDto = new FAQRequestDto();
        requestDto.setCategoria(CATEGORIA);
        requestDto.setPlataforma(PLATAFORMA);
        requestDto.setRelevancia(RELEVANCIA);
        requestDto.setStatus(STATUS);
        requestDto.setPergunta(PERGUNTA);
        requestDto.setResposta(RESPOSTA);

        return requestDto;
    }

    private static FAQ buildFAQ() {
        FAQ faq = new FAQ();
        faq.setId(ID);
        faq.setPergunta(PERGUNTA);
        faq.setResposta(RESPOSTA);
        faq.setRelevancia(RELEVANCIA);
        faq.setPlataforma(PLATAFORMA);
        faq.setCategoria(CATEGORIA);
        faq.setStatus(STATUS);

        return faq;
    }

    private static ResponseDTO<FAQ> buildResponse(List<FAQ> faqList) {

        ResponseDTO<FAQ> responseDTO = new ResponseDTO<>();
        responseDTO.setContent(faqList);
        responseDTO.setNumber(1);
        responseDTO.setSize(1);
        responseDTO.setTotalPages(1);
        responseDTO.setFirstPage(true);
        responseDTO.setHasPreviousPage(false);
        responseDTO.setHasNextPage(false);
        responseDTO.setHasContent(true);
        responseDTO.setFirst(true);
        responseDTO.setLast(true);
        responseDTO.setNextPage(1);
        responseDTO.setPreviousPage(1);
        responseDTO.setTotalElements(1);

        return responseDTO;
    }
}
