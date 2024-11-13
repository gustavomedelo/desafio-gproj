package br.com.desafio.gproj.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.nonNull;

@UtilityClass
public class Utils {

    public static boolean nonNullAndNonEmpty(String field) {
        return nonNull(field) && !field.isEmpty();
    }

    public static LocalDate formatStringToLocalDate(String date) {
        return LocalDate.parse(date);
    }

    public static String formatLocalDateToString(LocalDate date) {
        var dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateFormat.format(date);
    }
}
