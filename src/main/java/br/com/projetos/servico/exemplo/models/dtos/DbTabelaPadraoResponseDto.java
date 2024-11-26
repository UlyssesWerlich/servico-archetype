package br.com.projetos.servico.exemplo.models.dtos;

import br.com.projetos.servico.exemplo.models.enums.ColunaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DbTabelaPadraoResponseDto implements Serializable {

    private Long id;
    private String colunaTexto;
    private Integer colunaNumero;
    private BigDecimal colunaDecimal;
    private ColunaEnum colunaEnum;
    private LocalDate colunaDt;
    private Boolean status;

}
