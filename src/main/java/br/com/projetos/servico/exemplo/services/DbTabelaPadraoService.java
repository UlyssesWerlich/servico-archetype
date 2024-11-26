package br.com.projetos.servico.exemplo.services;

import br.com.projetos.libs.exceptions.BusinessException;
import br.com.projetos.servico.exemplo.models.builders.DbTabelaPadraoBuilder;
import br.com.projetos.servico.exemplo.models.builders.DbTabelaPadraoDtoBuilder;
import br.com.projetos.servico.exemplo.models.dtos.DbTabelaPadraoRequestDto;
import br.com.projetos.servico.exemplo.models.dtos.DbTabelaPadraoResponseDto;
import br.com.projetos.servico.exemplo.repositories.DbTabelaPadraoRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

import static br.com.projetos.servico.exemplo.exceptions.ErrorCodeEnum.ERRO_DB_TABELA_PADRAO_NAO_ENCONTRADO;
import static br.com.projetos.servico.exemplo.models.entities.QDbTabelaPadrao.dbTabelaPadrao;
import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DbTabelaPadraoService {

    private final DbTabelaPadraoRepository dbTabelaPadraoRepository;

    public Page<DbTabelaPadraoResponseDto> listar(Boolean status, String texto, Integer numero, LocalDate dt, BigDecimal decimalGt, BigDecimal decimalLt, Pageable pageable) {
        BooleanExpression predicate = Expressions.TRUE
                .and( ofNullable(status).map(dbTabelaPadrao.status::eq).orElse(null))
                .and( ofNullable(texto).map(dbTabelaPadrao.colunaTexto::eq).orElse(null))
                .and( ofNullable(numero).map(dbTabelaPadrao.colunaNumero::eq).orElse(null))
                .and( ofNullable(dt).map(dbTabelaPadrao.colunaDt::eq).orElse(null))
                .and( ofNullable(decimalGt).map(dbTabelaPadrao.colunaDecimal::gt).orElse(null))
                .and( ofNullable(decimalLt).map(dbTabelaPadrao.colunaDecimal::lt).orElse(null))
        ;
        return this.dbTabelaPadraoRepository.findAll(predicate, pageable)
                .map(tp -> new DbTabelaPadraoDtoBuilder().withEntity(tp).build())
        ;
    }

    @Cacheable("EXEMPLO:TABELA_PADRAO:ID:%1$s")
    public DbTabelaPadraoResponseDto buscarPorId(Long id) {
        return this.dbTabelaPadraoRepository.findById(id)
                .map(tp -> new DbTabelaPadraoDtoBuilder().withEntity(tp).build())
                .orElseThrow(() -> new BusinessException(NOT_FOUND, ERRO_DB_TABELA_PADRAO_NAO_ENCONTRADO, id));
    }

    public DbTabelaPadraoResponseDto criar(DbTabelaPadraoRequestDto dbTabelaPadraoRequestDto) {
        var dbTabelaPadrao = new DbTabelaPadraoBuilder().withRequest(dbTabelaPadraoRequestDto).build();
        var saved = this.dbTabelaPadraoRepository.saveAndFlush(dbTabelaPadrao);
        return new DbTabelaPadraoDtoBuilder().withEntity(saved).build();
    }

    public DbTabelaPadraoResponseDto atualizar(Long id, DbTabelaPadraoRequestDto dbTabelaPadraoRequestDto) {
        var dbTabelaPadrao = this.dbTabelaPadraoRepository.findById(id)
                .orElseThrow(() -> new BusinessException(NOT_FOUND, ERRO_DB_TABELA_PADRAO_NAO_ENCONTRADO, id));
        var updated = new DbTabelaPadraoBuilder(dbTabelaPadrao).withRequest(dbTabelaPadraoRequestDto).build();
        var saved = this.dbTabelaPadraoRepository.saveAndFlush(updated);
        return new DbTabelaPadraoDtoBuilder().withEntity(saved).build();
    }

    public void ativar(Long id) {
        var dbTabelaPadrao = this.dbTabelaPadraoRepository.findById(id)
                .orElseThrow(() -> new BusinessException(NOT_FOUND, ERRO_DB_TABELA_PADRAO_NAO_ENCONTRADO, id));
        dbTabelaPadrao.setStatus(true);
        this.dbTabelaPadraoRepository.saveAndFlush(dbTabelaPadrao);
    }

    public void desativar(Long id) {
        var dbTabelaPadrao = this.dbTabelaPadraoRepository.findById(id)
                .orElseThrow(() -> new BusinessException(NOT_FOUND, ERRO_DB_TABELA_PADRAO_NAO_ENCONTRADO, id));
        dbTabelaPadrao.setStatus(false);
        this.dbTabelaPadraoRepository.saveAndFlush(dbTabelaPadrao);
    }
}
