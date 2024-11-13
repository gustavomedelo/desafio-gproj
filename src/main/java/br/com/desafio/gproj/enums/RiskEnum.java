package br.com.desafio.gproj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum RiskEnum {
    BAIXO(1L, "Baixo"),
    MEDIO(2L, "Médio"),
    ALTO(3L, "Alto");

    private Long id;
    private String descricao;
    private static final Map<Long, RiskEnum> values = new HashMap<>();

    static {
        for (RiskEnum e : values()) {
            values.put(e.id, e);
        }
    }

    public static RiskEnum getById(Long id) {
        return values.get(id);
    }
}
