package br.com.desafio.gproj.exception.handler;

import br.com.desafio.gproj.exception.BusinessException;
import br.com.desafio.gproj.exception.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

import java.util.Collections;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ExceptionHandler {

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    ErrorMessageDTO handleNotReadableException(HttpMessageNotReadableException e, HttpServletRequest httpServletRequest) {
        return ErrorMessageDTO.builder()
                .message(Collections.singletonList(e.getMessage()))
                .error(e.getClass().getSimpleName())
                .path(httpServletRequest.getRequestURI())
                .build();
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    ErrorMessageDTO handleValidationExceptions(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {
        return ErrorMessageDTO.builder()
                .message(e.getBindingResult()
                        .getAllErrors()
                        .parallelStream()
                        .map(objectError -> format("{0}: {1}", ((FieldError) objectError).getField(), (objectError).getDefaultMessage()))
                        .sorted()
                        .collect(Collectors.toList()))
                .error(e.getClass().getSimpleName())
                .path(httpServletRequest.getRequestURI())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNoHandlerFoundException (NoHandlerFoundException e, HttpServletRequest httpServletRequest) {
        return new ModelAndView("error404");
    }

    @ResponseBody
    @ResponseStatus(CONFLICT)
    @org.springframework.web.bind.annotation.ExceptionHandler
    ErrorMessageDTO handleBusinessException(BusinessException e, HttpServletRequest httpServletRequest) {
        return ErrorMessageDTO.builder()
                .message(e.getDetails())
                .error(e.getMessage())
                .path(httpServletRequest.getRequestURI())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ModelAndView handleException(HttpServletRequest request, Exception ex) {
        var modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex.getLocalizedMessage());
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
