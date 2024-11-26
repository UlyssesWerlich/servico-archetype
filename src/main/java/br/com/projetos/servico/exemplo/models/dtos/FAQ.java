package br.com.projetos.servico.exemplo.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FAQ {

    private Long id;
    private String pergunta;
    private String resposta;
    private Integer relevancia;
    private String plataforma;
    private String categoria;
    private String status;

}
