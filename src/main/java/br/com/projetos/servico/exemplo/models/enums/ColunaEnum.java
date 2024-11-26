package br.com.projetos.servico.exemplo.models.enums;

public enum ColunaEnum {

    OPTION_01(0, "Enum Option 01"),
    OPTION_02(1, "Enum Option 02"),
    OPTION_03(2, "Enum Option 03");

    private final Integer value;
    private final String description;

    ColunaEnum(final Integer value, final String description) {
        this.value = value;
        this.description = description;
    }

    public static ColunaEnum fromValue(Integer value) {
        for (ColunaEnum status : values()) {
            if (status.toValue().equals(value)) return status;
        }
        throw new IllegalArgumentException(String.format("Enum value is invalid %s", value));
    }

    public String getDescription() {
        return description;
    }

    public Integer toValue() {
        return value;
    }

    public String toString() {
        return value.toString();
    }
}
