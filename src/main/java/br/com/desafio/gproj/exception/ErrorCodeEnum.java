package br.com.desafio.gproj.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    ERROR_MANAGER_NOT_FOUND("error.manager.not.found"),
    ERROR_PROJECT_NOT_FOUND("error.project.not.found"),
    ERROR_PROJECT_DELETE("error.project.delete"),
    ERROR_PROJECT_ALREADY_EXISTS("error.project.already.exists"),
    ERROR_PERSON_NOT_FOUND("error.person.not.found"),
    ERROR_PERSON_ALREADY_EXISTS("error.person.already.exists"),
    ERROR_MEMBER_ALREADY_EXISTS("error.member.already.exists"),
    ERROR_MEMBER_ALREADY_EXISTS_DETAILS("error.member.already.exists.details");

    private final String messageKey;
}
