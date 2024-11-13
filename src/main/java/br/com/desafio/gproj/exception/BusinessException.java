package br.com.desafio.gproj.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private final HttpStatus status;
    private final String message;
    private final List<String> details;
}
