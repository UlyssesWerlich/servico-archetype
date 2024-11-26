package br.com.projetos.servico.exemplo.controllers.v1;

import br.com.projetos.servico.exemplo.models.dtos.FAQ;
import br.com.projetos.servico.exemplo.models.dtos.FAQRequestDto;
import br.com.projetos.servico.exemplo.models.dtos.ResponseDTO;
import br.com.projetos.servico.exemplo.services.FAQService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/faq")
@RequiredArgsConstructor
public class FAQController {

    private final FAQService faqService;

    @GetMapping("/{id}")
    public ResponseEntity<FAQ> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(faqService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<FAQ>> listar(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String plataforma,
            @RequestParam(required = false) Integer relevancia,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String pergunta,
            @RequestParam(required = false) String resposta
    ){
        return ResponseEntity.ok(faqService.listar(categoria, plataforma, relevancia, status, pergunta, resposta));
    }

    @PostMapping
    public ResponseEntity<FAQ> criar(@RequestBody FAQRequestDto faqRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(faqService.criar(faqRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FAQ> atualizar(@PathVariable Long id, @RequestBody FAQRequestDto faqRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(faqService.atualizar(id, faqRequestDto));
    }

}
