package br.com.projetos.servico.exemplo.models.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class DbTabelaPadraoRequestDto implements Serializable {

    @NotNull
    @NotBlank
    private String colunaTexto;

    @NotNull
    private Integer colunaNumero;

    @NotNull
    private BigDecimal colunaDecimal;

    @NotNull
    @Min(0) @Max(2)
    private Integer colunaEnum;

    @NotNull
    private LocalDate colunaDt;

}
