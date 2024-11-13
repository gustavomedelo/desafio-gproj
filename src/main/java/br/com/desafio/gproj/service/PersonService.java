package br.com.desafio.gproj.service;

import br.com.desafio.gproj.dto.CreatePersonDTO;
import br.com.desafio.gproj.exception.BusinessException;
import br.com.desafio.gproj.helper.MessageHelper;
import br.com.desafio.gproj.model.Person;
import br.com.desafio.gproj.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.desafio.gproj.exception.ErrorCodeEnum.ERROR_MANAGER_NOT_FOUND;
import static br.com.desafio.gproj.exception.ErrorCodeEnum.ERROR_PERSON_ALREADY_EXISTS;
import static br.com.desafio.gproj.exception.ErrorCodeEnum.ERROR_PERSON_NOT_FOUND;
import static br.com.desafio.gproj.mapper.PersonMapper.map;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {

    private final MessageHelper messageHelper;
    private final PersonRepository personRepository;

    public void create(final CreatePersonDTO createPersonDTO) {
        validateIfExistsByCpf(createPersonDTO.getCpf());
        personRepository.save(map(createPersonDTO));
    }

    private void validateIfExistsByCpf(final String cpf) {
        personRepository.findByCpf(cpf)
                .ifPresent(person -> {
                    log.error(messageHelper.get(ERROR_PERSON_ALREADY_EXISTS, person.getCpf()));
                    throw BusinessException.builder()
                            .status(CONFLICT)
                            .message(messageHelper.get(ERROR_PERSON_ALREADY_EXISTS, person.getCpf()))
                            .build();
                });
    }

    public Person findById(final Long id, final boolean isManager) {
        var errorCodeEnum = isManager ? ERROR_MANAGER_NOT_FOUND : ERROR_PERSON_NOT_FOUND;
        return personRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(messageHelper.get(errorCodeEnum, id));
                    return BusinessException.builder()
                            .status(NOT_FOUND)
                            .message(messageHelper.get(errorCodeEnum, id))
                            .build();
                });
    }

    public List<Person> findAllManager() {
        return personRepository.findByManagerAndEmployee(Boolean.TRUE, Boolean.FALSE);
    }

    public List<Person> findAllEmployee() {
        return personRepository.findByManagerAndEmployee(Boolean.FALSE, Boolean.TRUE);
    }
}
