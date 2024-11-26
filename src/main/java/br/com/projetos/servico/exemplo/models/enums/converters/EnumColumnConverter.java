package br.com.projetos.servico.exemplo.models.enums.converters;

import br.com.projetos.servico.exemplo.models.enums.ColunaEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import static java.util.Objects.nonNull;

@Converter
public class EnumColumnConverter implements AttributeConverter<ColunaEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ColunaEnum attribute) {
        return nonNull(attribute) ? attribute.toValue() : null;
    }

    @Override
    public ColunaEnum convertToEntityAttribute(Integer column) {
        return nonNull(column) ? ColunaEnum.fromValue(column) : null;
    }
}
