package br.com.projetos.servico.exemplo.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ResponseDTO<T> implements Serializable {

    private List<T> content;
    private Integer number;
    private Integer size;
    private Integer totalPages;
    private Boolean firstPage;
    private Boolean hasPreviousPage;
    private Boolean hasNextPage;
    private Boolean hasContent;
    private Boolean first;
    private Boolean last;
    private Integer nextPage;
    private Integer previousPage;
    private Integer totalElements;

    public ResponseDTO(){}

    public ResponseDTO(ResponseDTO<?> content, List<T> list) {
        this.first = content.getFirst();
        this.firstPage = content.getFirstPage();
        this.hasContent = content.getHasContent();
        this.hasNextPage = content.getHasNextPage();
        this.hasPreviousPage = content.getHasPreviousPage();
        this.last = content.getLast();
        this.nextPage = content.getNextPage();
        this.number = content.getNumber();
        this.previousPage = content.getPreviousPage();
        this.size = content.getSize();
        this.totalElements = content.getTotalElements();
        this.totalPages = content.getTotalPages();
        this.content = list;
    }
}
