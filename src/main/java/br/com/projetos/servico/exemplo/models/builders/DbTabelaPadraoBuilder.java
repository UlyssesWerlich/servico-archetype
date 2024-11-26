package br.com.projetos.servico.exemplo.models.builders;

import br.com.projetos.servico.exemplo.models.entities.DbTabelaPadrao;
import br.com.projetos.servico.exemplo.models.dtos.DbTabelaPadraoRequestDto;
import br.com.projetos.servico.exemplo.models.enums.ColunaEnum;

import java.time.LocalDateTime;

public class DbTabelaPadraoBuilder {

    private final DbTabelaPadrao dbTabelaPadrao;

    public DbTabelaPadraoBuilder(DbTabelaPadrao dbTabelaPadrao){
        this.dbTabelaPadrao = dbTabelaPadrao;
        this.dbTabelaPadrao.setColunaDh(LocalDateTime.now());
    }

    public DbTabelaPadraoBuilder(){
        this.dbTabelaPadrao = new DbTabelaPadrao();
        this.dbTabelaPadrao.setColunaDh(LocalDateTime.now());
    }

    public DbTabelaPadraoBuilder withRequest(DbTabelaPadraoRequestDto dto){

        this.dbTabelaPadrao.setColunaTexto(dto.getColunaTexto());
        this.dbTabelaPadrao.setColunaNumero(dto.getColunaNumero());
        this.dbTabelaPadrao.setColunaDecimal(dto.getColunaDecimal());
        this.dbTabelaPadrao.setColunaEnum(ColunaEnum.fromValue(dto.getColunaEnum()));
        this.dbTabelaPadrao.setColunaDt(dto.getColunaDt());
        this.dbTabelaPadrao.setStatus(true);

        return this;
    }

    public DbTabelaPadrao build(){
        return this.dbTabelaPadrao;
    }

}
