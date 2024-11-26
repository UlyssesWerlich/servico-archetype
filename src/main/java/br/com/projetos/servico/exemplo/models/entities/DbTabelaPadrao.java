package br.com.projetos.servico.exemplo.models.entities;

import br.com.projetos.servico.exemplo.models.enums.ColunaEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "db_tabela_padrao", schema = "exemplo")
public class DbTabelaPadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "numeric(19, 0)")
    private Long id;

    @Column(name = "coluna_texto", columnDefinition = "nvarchar(100)")
    private String colunaTexto;

    @Column(name = "coluna_numero", columnDefinition = "numeric(10, 0)")
    private Integer colunaNumero;

    @Column(name = "coluna_decimal", columnDefinition = "numeric(19, 2)")
    private BigDecimal colunaDecimal;

    @Column(name = "coluna_enum", columnDefinition = "smallint")
    private ColunaEnum colunaEnum;

    @Column(name = "coluna_dt", columnDefinition = "date")
    private LocalDate colunaDt;

    @Column(name = "coluna_dh", columnDefinition = "datetime")
    private LocalDateTime colunaDh;

    @Column(name = "status", columnDefinition = "bit")
    private Boolean status;

}
