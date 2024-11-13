package br.com.desafio.gproj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    EM_ANALISE(1L, "Em análise"),
    ANALISE_REALIZADA(2L, "Análise realizada"),
    ANALISE_APROVADA(3L, "Análise aprovada"),
    INICIADO(4L, "Iniciado"),
    PLANEJADO(5L, "Planejado"),
    EM_ANDAMENTO(6L, "Em andamento"),
    ENCERRADO(7L, "Encerrado"),
    CANCELADO(8L, "Cancelado");

    private Long id;
    private String descricao;
    private static final Map<Long, StatusEnum> values = new HashMap<>();

    static {
        for (StatusEnum e : values()) {
            values.put(e.id, e);
        }
    }

    public static StatusEnum getById(Long id) {
        return values.get(id);
    }
}
