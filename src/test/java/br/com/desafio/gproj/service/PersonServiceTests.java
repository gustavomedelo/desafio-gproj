package br.com.desafio.gproj.service;

import br.com.desafio.gproj.dto.CreatePersonDTO;
import br.com.desafio.gproj.enums.RoleEnum;
import br.com.desafio.gproj.exception.BusinessException;
import br.com.desafio.gproj.helper.MessageHelper;
import br.com.desafio.gproj.model.Person;
import br.com.desafio.gproj.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PersonServiceTests {

    @InjectMocks
    private PersonService personService;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private MessageHelper messageHelper;

    @Test
    void shouldSucess_whenCreatePerson() {
        var createPersonDTO = CreatePersonDTO.builder()
                .cpf("12345678901")
                .role(RoleEnum.FUNCIONARIO)
                .build();
        when(personRepository.findByCpf(any())).thenReturn(Optional.empty());

        personService.create(createPersonDTO);

        verify(personRepository, times(1)).findByCpf(any());
        verify(personRepository, times(1)).save(any());
    }

    @Test
    void shouldThrow_whenPersonAlreadyExists() {
        var createPersonDTO = CreatePersonDTO.builder()
                .cpf("12345678901")
                .birthDate("1991-01-23")
                .role(RoleEnum.FUNCIONARIO)
                .build();
        var person = Person.builder()
                .cpf("12345678901")
                .build();
        when(personRepository.findByCpf(person.getCpf())).thenReturn(Optional.of(person));

        assertThrows(BusinessException.class, () ->
                personService.create(createPersonDTO)
        );

        verify(personRepository, never()).save(any(Person.class));
    }
}

