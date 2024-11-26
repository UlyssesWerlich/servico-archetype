package br.com.projetos.servico.exemplo.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FAQRequestDto {

    private String categoria;
    private String plataforma;
    private Integer relevancia;
    private String status;
    private String pergunta;
    private String resposta;

}
