package br.com.projetos.servico.exemplo.models.builders;

import br.com.projetos.servico.exemplo.models.entities.DbTabelaPadrao;
import br.com.projetos.servico.exemplo.models.dtos.DbTabelaPadraoResponseDto;

public class DbTabelaPadraoDtoBuilder {

    private final DbTabelaPadraoResponseDto dbTabelaPadraoResponseDto;

    public DbTabelaPadraoDtoBuilder() {
        this.dbTabelaPadraoResponseDto = new DbTabelaPadraoResponseDto();
    }

    public DbTabelaPadraoDtoBuilder withEntity(DbTabelaPadrao dbTabelaPadrao){

        this.dbTabelaPadraoResponseDto.setId(dbTabelaPadrao.getId());
        this.dbTabelaPadraoResponseDto.setColunaTexto(dbTabelaPadrao.getColunaTexto());
        this.dbTabelaPadraoResponseDto.setColunaNumero(dbTabelaPadrao.getColunaNumero());
        this.dbTabelaPadraoResponseDto.setColunaDecimal(dbTabelaPadrao.getColunaDecimal());
        this.dbTabelaPadraoResponseDto.setColunaEnum(dbTabelaPadrao.getColunaEnum());
        this.dbTabelaPadraoResponseDto.setColunaDt(dbTabelaPadrao.getColunaDt());
        this.dbTabelaPadraoResponseDto.setStatus(dbTabelaPadrao.getStatus());

        return this;
    }

    public DbTabelaPadraoResponseDto build(){
        return this.dbTabelaPadraoResponseDto;
    }
}
