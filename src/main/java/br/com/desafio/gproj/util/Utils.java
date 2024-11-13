package br.com.desafio.gproj.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;

import static java.util.Objects.nonNull;

@UtilityClass
public class Utils {

    public static boolean nonNullAndNonEmpty(String field) {
        return nonNull(field) && !field.isEmpty();
    }

    public static LocalDate formatStringToLocalDate(String date) {
        return LocalDate.parse(date);
    }
}
