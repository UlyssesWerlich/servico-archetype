package br.com.projetos.servico.exemplo.integrations;

import br.com.projetos.servico.exemplo.configs.IntegrationConfig;
import br.com.projetos.servico.exemplo.models.dtos.FAQ;
import br.com.projetos.servico.exemplo.models.dtos.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "FAQIntegration",
        url = "${api.externa.host}",
        path = "${api.externa.base-path}",
        configuration = IntegrationConfig.class
)
public interface FAQIntegration {

    @GetMapping(value = "${api.externa.faqs.buscar-por-id}")
    FAQ buscarPorId(@PathVariable("id") Long id);

    @GetMapping(value = "${api.externa.faqs.listar}")
    ResponseDTO<FAQ> listar(
            @RequestParam(name = "categoria", required = false) String categoria,
            @RequestParam(name = "plataforma", required = false) String plataforma,
            @RequestParam(name = "relevancia", required = false) Integer relevancia,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "pergunta", required = false) String pergunta,
            @RequestParam(name = "resposta", required = false) String resposta
    );

    @PostMapping(value = "${api.externa.faqs.criar}")
    FAQ criar(
            @RequestParam(name = "categoria", required = false) String categoria,
            @RequestParam(name = "plataforma", required = false) String plataforma,
            @RequestParam(name = "relevancia", required = false) Integer relevancia,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "pergunta") String pergunta,
            @RequestParam(name = "resposta") String resposta
    );

    @PutMapping(value = "${api.externa.faqs.atualizar}")
    FAQ atualizar(
            @PathVariable("id") Long id,
            @RequestParam(name = "categoria", required = false) String categoria,
            @RequestParam(name = "plataforma", required = false) String plataforma,
            @RequestParam(name = "relevancia", required = false) Integer relevancia,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "pergunta") String pergunta,
            @RequestParam(name = "resposta") String resposta
    );
}
