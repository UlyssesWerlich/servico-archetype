package br.com.projetos.servico.exemplo.services;

import br.com.projetos.servico.exemplo.integrations.FAQIntegration;
import br.com.projetos.servico.exemplo.models.dtos.FAQ;
import br.com.projetos.servico.exemplo.models.dtos.FAQRequestDto;
import br.com.projetos.servico.exemplo.models.dtos.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FAQService {

    private final FAQIntegration FAQIntegration;

    public FAQ buscarPorId(Long id){
        return FAQIntegration.buscarPorId(id);
    }

    public ResponseDTO<FAQ> listar(
            String categoria,
            String plataforma,
            Integer relevancia,
            String status,
            String pergunta,
            String resposta
    ){
        return FAQIntegration.listar(categoria, plataforma, relevancia, status, pergunta, resposta);
    }

    public FAQ criar(FAQRequestDto faqRequestDto){
        return FAQIntegration.criar(
            faqRequestDto.getCategoria(),
            faqRequestDto.getPlataforma(),
            faqRequestDto.getRelevancia(),
            faqRequestDto.getStatus(),
            faqRequestDto.getPergunta(),
            faqRequestDto.getResposta()
        );
    }

    public FAQ atualizar(Long id, FAQRequestDto faqRequestDto){
        return FAQIntegration.atualizar(id,
            faqRequestDto.getCategoria(),
            faqRequestDto.getPlataforma(),
            faqRequestDto.getRelevancia(),
            faqRequestDto.getStatus(),
            faqRequestDto.getPergunta(),
            faqRequestDto.getResposta()
        );
    }
}
