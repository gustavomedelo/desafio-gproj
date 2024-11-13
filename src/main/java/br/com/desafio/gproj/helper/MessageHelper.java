package br.com.desafio.gproj.helper;

import br.com.desafio.gproj.exception.ErrorCodeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageHelper {

    private MessageSourceAccessor accessor;
    private final MessageSource messageSource;

    @PostConstruct
    public void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
    }

    public String get(ErrorCodeEnum code, Object... args) {
        return accessor.getMessage(code.getMessageKey(), args);
    }
}
