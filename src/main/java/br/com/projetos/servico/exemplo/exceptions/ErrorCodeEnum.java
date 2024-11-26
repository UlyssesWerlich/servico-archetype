package br.com.projetos.servico.exemplo.exceptions;

import br.com.projetos.libs.exceptions.ErrorCode;
import lombok.Getter;

@Getter
public enum ErrorCodeEnum implements ErrorCode {

    ERRO_ELEMENTO_GENERICO_NAO_ENCONTRADO("erro.elemento.generico.nao.encontrado"),

    ERRO_DB_TABELA_PADRAO_NAO_ENCONTRADO("erro.db-tabela-padrao.nao.encontrado"),
    ;

    private final String messageKey;

    ErrorCodeEnum(String messageKey) {
        this.messageKey = messageKey;
    }

    @Override
    public String toString() {
        return messageKey;
    }
}
